package com.wangwei.cloud.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wangwei.cloud.entity.AuthUserDetails;
import com.wangwei.cloud.entity.Role;
import com.wangwei.cloud.entity.User;
import com.wangwei.cloud.mapper.UserMapper;
import com.wangwei.cloud.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
   /* @Autowired
    RedisUtil redisUtil;*/
    public UserDetails loadUserByUsername(String userCode) throws UsernameNotFoundException {
        System.out.println("134444444444");
        QueryWrapper<User> queryWrapper2 = new  QueryWrapper<>();
        queryWrapper2.eq("user_name",userCode);
        User user = userMapper.selectOne(queryWrapper2);
        List<Role> lists=userMapper.getUserRoles(userCode);
        user.setRoles(lists);
        user.setDefaultRole(lists.get(0));
        if(user == null){
            log.info("登录用户【"+userCode + "】不存在.");
            throw new UsernameNotFoundException("登录用户【"+userCode + "】不存在.");
        }
        UserDetails aa =new AuthUserDetails(user);
        return aa;
    }



}
