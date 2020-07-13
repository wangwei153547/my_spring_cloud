package com.wangwei.cloud.controller;

import com.alibaba.fastjson.JSON;
import com.wangwei.cloud.entity.AuthUserDetails;
import com.wangwei.cloud.entity.Role;
import com.wangwei.cloud.entity.User;
import com.wangwei.cloud.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.OncePerRequestFilter;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@CrossOrigin
@RestController
public class UserController {
     @Autowired
    RedisUtil redisUtil;
    @PostMapping("/oauth/me")
    public User getUser(@RequestParam String access_token){
         AuthUserDetails userDetails = (AuthUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         User user2=userDetails.getUser() ;

        /* if(redisUtil.get("userRole"+userDetails.getUserCode())==null) {
             user2.setDefaultRole(userDetails.getDefaultRole());
             redisUtil.set("userRole"+userDetails.getUserCode(),userDetails.getDefaultRole(),7200);
         }else{
             user2.setDefaultRole((Role) redisUtil.get("userRole"+userDetails.getUserCode()));
         }*/



        return   user2 ;

    }




     @PostMapping("/oauth/updateDefaultRole")
    public boolean updateDefaultRole(@RequestParam String access_token2,@RequestParam String roleCode) {
         System.out.println(123456);

            User user2= (User) redisUtil.get("user"+access_token2);
            System.out.println(user2.toString());
            List<Role> list=user2.getRoles();
            list.forEach(role->{
              if(roleCode.equals(role.getRoleCode())){
                  user2.setDefaultRole(role);
                  redisUtil.set("user"+access_token2,user2,redisUtil.getExpire("user"+access_token2));
              }

            }  );
            return  true;


    }

    @GetMapping("api/user")
    public Principal user(Principal user){
        System.out.println(".. 进入　获取用户信息　方法   ..........  ");
        System.out.println(JSON.toJSONString(user));
        return user;
    }




    @RequestMapping(path = "api/messages", method = RequestMethod.GET)
    public List<String> getMessages( ) {
        List<String> list = new LinkedList<>();
        list.add("张三");
        list.add("李四");
        //ThreadLocal
        return list;
    }

    @RequestMapping(path = "api/messages", method = RequestMethod.POST)
    public String postMessage(Principal principal) {
        return "POST -> 默苍离 ";
    }

    /**
     * 当前登录人信息
     * @return
     */
    @RequestMapping(path = "api/loginUser", method = RequestMethod.GET)
    public UserDetails currentlyLoginUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return  userDetails;
    }
    @PostMapping("/country")
    public String queryCountry( @RequestParam String access_token )    {

        UserDetails  userDetails = (UserDetails ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AuthUserDetails aa=(AuthUserDetails)userDetails;
       System.out.println(userDetails.toString());
        return    "user" ;
    }

}
