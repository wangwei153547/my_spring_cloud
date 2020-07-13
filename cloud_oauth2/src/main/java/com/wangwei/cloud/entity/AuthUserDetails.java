package com.wangwei.cloud.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.wangwei.cloud.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AuthUserDetails   implements UserDetails {

    private User user;



    public AuthUserDetails(User user) {
        if (user != null) {
           this.setUser(user);

        }
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    //将角色权限 放入GrantedAuthorit的自定义实现类MyGrantedAuthority中  为权限判定提供数据
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<Role> lists=this.getUser().getRoles();
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        List<GrantedAuthority> authList2 = new ArrayList<GrantedAuthority>();
        lists.forEach(Role->{
            if("Y".equals(Role.getIsDefault())){
                authList2.add(new SimpleGrantedAuthority(Role.getRoleCode()));
            }else {
                authList.add(new SimpleGrantedAuthority(Role.getRoleCode()));
            }
        });
        authList2.addAll(authList);
        return authList2;
    }

    @Override
    public String getPassword() {

        return   BCrypt.hashpw(this.getUser().getUserPwd(), BCrypt.gensalt()) ;
    }

    @Override
    public String getUsername() {
        return this.getUser().getUserName();
    }


    /**
     * 账户是否过期
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    /**
     * 是否禁用
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    /**
     * 密码是否过期
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    /**
     * 是否启用
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        if("N".equals(this.getUser().getIsUse())) {
            return false;
        }else {
            return true;
        }
    }
}