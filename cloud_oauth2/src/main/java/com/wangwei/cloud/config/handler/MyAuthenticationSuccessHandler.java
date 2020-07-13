package com.wangwei.cloud.config.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wangwei.cloud.entity.AuthUserDetails;

import com.wangwei.cloud.entity.User;

import com.wangwei.cloud.utils.RedisUtil;

import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.encoders.Base64;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import java.util.Map;

@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;
    @Autowired
    RedisUtil redisUtil;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String header = request.getHeader("Authorization");
        if (header == null || !header.toLowerCase().startsWith("basic ")) {
            throw new UnapprovedClientAuthenticationException("请求头中没有clientId");
        }

        String[] tokens = extractAndDecodeHeader(header, request);
        assert tokens.length == 2;

        String clientId = tokens[0];
        String clientSecret = tokens[1];
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
        if (clientDetails == null){
            throw new UnapprovedClientAuthenticationException("clientId 不存在"+clientId);
            //判断  方言  是否一致
        }else if (!StringUtils.equals(clientDetails.getClientSecret(),clientSecret)){
            throw new UnapprovedClientAuthenticationException("clientSecret 不匹配"+clientId);
        }

        // grantType 为自定义的"custom"
        TokenRequest tokenRequest = new TokenRequest(new HashMap<>(), clientId, clientDetails.getScope(), request.getParameter("grant_type"));
        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
        OAuth2AccessToken token = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
        String grant_type=  request.getParameter("grant_type");
        if("password".equals(grant_type)){
            User user= ((AuthUserDetails) authentication.getPrincipal()).getUser();
            redisUtil.set("user"+token.getValue(),user,token.getExpiresIn());
            redisUtil.set("user"+token.getRefreshToken().getValue(),user,token.getExpiresIn());
        }else{
            redisUtil.expire("user"+token.getValue(),token.getExpiresIn());
            redisUtil.set("user"+token.getRefreshToken().getValue(),redisUtil.get("user"+token.getValue()),token.getExpiresIn());
        }
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("userMsg",((AuthUserDetails) authentication.getPrincipal()).getUser());
        map.put("tokenMsg", JSONObject.parseObject(objectMapper.writeValueAsString(token)));
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString( map, SerializerFeature.DisableCircularReferenceDetect) );

    }


    /**
     * Decodes the header into a username and password.
     *
     * @throws BadCredentialsException if the Basic header is not present or is not valid
     * Base64
     */
    /**
     * 解码请求头
     */
    private String[] extractAndDecodeHeader(String header, HttpServletRequest request) throws IOException {
        byte[] base64Token = header.substring(6).getBytes("UTF-8");

        byte[] decoded;
        try {
            decoded = Base64.decode(base64Token);
        } catch (IllegalArgumentException var7) {
            throw new BadCredentialsException("Failed to decode basic authentication token");
        }

        String token = new String(decoded, "UTF-8");
        int delim = token.indexOf(":");
        if (delim == -1) {
            throw new BadCredentialsException("Invalid basic authentication token");
        } else {
            return new String[]{token.substring(0, delim), token.substring(delim + 1)};
        }
    }
}