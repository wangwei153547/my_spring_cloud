package com.wangwei.cloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wangwei.cloud.entity.Role;
import com.wangwei.cloud.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-03-09
 */
public interface UserMapper extends BaseMapper<User> {
    @Select("select a2.*,a1.is_default from sys_user_role a1,sys_role a2 where a1.role_id=a2.role_id " +
            "and a1.user_id=(select user_id from sys_user where user_code=#{userCode})")
    public List<Role> getUserRoles(String userCode);
}
