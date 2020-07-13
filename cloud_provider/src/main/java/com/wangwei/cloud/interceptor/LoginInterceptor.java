package com.wangwei.cloud.interceptor;

import com.wangwei.cloud.config.FeignOauth;
import com.wangwei.cloud.sys.entity.User;
import com.wangwei.cloud.util.RedisUtil;
import org.apache.ibatis.plugin.Intercepts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 在请求被处理之前调用
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Autowired
    FeignOauth feignOauth;
    @Autowired
    RedisUtil redisUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 检查每个到来的请求对应的session域中是否有登录标识
      System.out.println(request.getRequestURL());
         String access_token=request.getHeader("access_token");
      /*   User user2= (User) redisUtil.get("access_token");
        if(user2!=null){
            redisUtil.expire("access_token",7200);
            UserThreadLocal.set(user2);
            return true;
        } */
        User user= (User) redisUtil.get("user"+access_token);
      /*   try {
            user = feignOauth.getUser(access_token);
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }*/

        if (null == user  ) {
            // 未登录，重定向到登录页
            response.setStatus(401);
            return false;
        }
        redisUtil.set("access_token",user,7200);
        UserThreadLocal.set(user);
        return true;
    }

    /**
     * 在请求被处理后，视图渲染之前调用
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在整个请求结束后调用
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserThreadLocal.set(null);
    }
}
