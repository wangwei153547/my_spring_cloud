package com.wangwei.cloud.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wangwei.cloud.entity.User;
import com.wangwei.cloud.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("五十年代1");
        QueryWrapper<User> queryWrapper2 = new  QueryWrapper<>();
        queryWrapper2.eq("user_name",username);
        User user = userMapper.selectOne(queryWrapper2);
        System.out.println(user.getUserName());
        if(user == null){
            log.info("登录用户【"+username + "】不存在.");
            throw new UsernameNotFoundException("登录用户【"+username + "】不存在.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), BCrypt.hashpw(user.getUserPwd(), BCrypt.gensalt()),true,true,true,true, getAuthority());
    }

    private List getAuthority() {

        System.out.println("五十年代");
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }


}
