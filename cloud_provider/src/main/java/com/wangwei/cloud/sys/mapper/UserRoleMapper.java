package com.wangwei.cloud.sys.mapper;

import com.wangwei.cloud.sys.entity.ModuleMenu;
import com.wangwei.cloud.sys.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-03-23
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {
    @Select("select \n" +
            "t.user_role_id,\n" +
            "t.user_id,\n" +
            "t.role_id,\n" +
            "t1.role_code,\n" +
            "t1.role_name,\n" +
            "t.is_use\n" +
            "from\n" +
            "sys_user_role t,\n" +
            "sys_role t1\n" +
            "where t.role_id=t1.role_id\n" +
            "and t.user_id=#{userId} ")
    public List<Map<String,Object>> getUserRoles(Long userId );
}
