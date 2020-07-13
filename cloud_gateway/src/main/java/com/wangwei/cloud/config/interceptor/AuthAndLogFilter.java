package com.wangwei.cloud.config.interceptor;


import com.wangwei.cloud.controller.User;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

//@Component
public class AuthAndLogFilter implements GlobalFilter, Ordered {

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        StringBuilder logBuilder = new StringBuilder();
        Map<String, String> params = parseRequest(exchange, logBuilder);
        String access_token =  params.get("access_token");
         System.out.println(exchange.getRequest().getPath());
         System.out.println(access_token);
     //   User user=new User();getUser
       // user.setIsUse("Y");
     //     User listttt=feignContoller.getUser("c36c0bfb-822b-46ae-9fb0-9e153c187691");
      // System.out.println(listttt.toString());
    //   if(access_token!=null&&!"".equals(access_token)) {
         //  User listttt=feignContoller.getUser(access_token);
         //  serverHttpResponse.getHeaders().set("user_msg",listttt);
           /* String url = "http://oauth2-server/oauth/me";
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("access_token", access_token);
            System.out.println(access_token);
            HttpHeaders headers = new HttpHeaders();
            headers.setBasicAuth("user-client", "user-secret-8888");
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.addAll(map);
            String respBody = restTemplate.postForObject(url, headers, String.class);
            serverHttpResponse.getHeaders().set("user_msg",respBody);*/
           // exchange.getRequest().getQueryParams().add("userMsg", respBody);
    //    }

        DataBufferFactory bufferFactory = serverHttpResponse.bufferFactory();
        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(serverHttpResponse) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                if (body instanceof Flux) {
                    Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
                    return super.writeWith(fluxBody.map(dataBuffer -> {
                        byte[] content = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(content);
                        DataBufferUtils.release(dataBuffer);
                        String resp = new String(content, Charset.forName("UTF-8"));
                        logBuilder.append(",resp=").append(resp);

                        byte[] uppedContent = new String(content, Charset.forName("UTF-8")).getBytes();
                        return bufferFactory.wrap(uppedContent);
                    }));
                }
                return super.writeWith(body);
            }
        };
        return chain.filter(exchange.mutate().response(decoratedResponse).build());
    }

    private Map<String, String> parseRequest(ServerWebExchange exchange, StringBuilder logBuilder) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        String method = serverHttpRequest.getMethodValue().toUpperCase();
        logBuilder.append(method).append(",").append(serverHttpRequest.getURI());
        MultiValueMap<String, String> query = serverHttpRequest.getQueryParams();
        Map<String, String> params = new HashMap<>();
        query.forEach((k, v) -> {
            params.put(k, v.get(0));
        });
        if("POST".equals(method)) {
            String body = exchange.getAttributeOrDefault("cachedRequestBody", "");
            if(StringUtils.isNotBlank(body)) {
                logBuilder.append(",body=").append(body);
                String[] kvArray = body.split("&");
                for (String kv : kvArray) {
                    if (kv.indexOf("=") >= 0) {
                        String k = kv.split("=")[0];
                        String v = kv.split("=")[1];
                        if(!params.containsKey(k)) {
                            try {
                                params.put(k, URLDecoder.decode(v, "UTF-8"));
                            } catch (UnsupportedEncodingException e) {
                            }
                        }
                    }
                }
            }
        }

        return params;
    }


    @Override
    public int getOrder() {
        return -20;
    }
}
