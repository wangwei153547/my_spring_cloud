package com.wangwei.cloud.config.handler;

import com.alibaba.fastjson.JSON;
import com.wangwei.cloud.util.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException, IOException {
        System.out.println("weeeeeeeeeeeeeeeeeeeeeeeee");
        logger.info("登录失败");
        //设置状态码
        response.setStatus(500);
        response.setContentType("application/json;charset=UTF-8");
        //将 登录失败 信息打包成json格式返回
        response.getWriter().write(JSON.toJSONString(Results.error(exception.getMessage())));
    }
}
