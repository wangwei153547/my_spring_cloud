package com.wangwei.cloud.sys.mapper;

import com.wangwei.cloud.sys.entity.ModuleMenu;
import com.wangwei.cloud.sys.entity.RoleModule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-03-23
 */

public interface RoleModuleMapper extends BaseMapper<RoleModule> {
    @Select("select a1.* from sys_module_menu a1 where module_levl=#{moduleLevl} and parent_module_id=#{parentModuleId} and  module_id in(select a2.module_id from " +
            "sys_role_module a2 where a2.role_id=(select a3.role_id from sys_user_role a3 where a3.user_id" +
            "=(select s4.user_id from sys_user s4 where s4.user_name=#{user_name})) )")
    public List<ModuleMenu> getModuleMenu(String user_name,int moduleLevl,int parentModuleId);

    @Select("select a1.* from sys_module_menu a1 where module_levl=#{moduleLevl} and parent_module_id=#{parentModuleId} ")
    public List<ModuleMenu> getModuleMenu3(int moduleLevl,int parentModuleId);

    @Select("select a1.* ,#{roleId} role_id,(select a2.is_use from sys_role_module a2 where a1.module_id=a2.module_id and a2.role_id=#{roleId} limit 1) is_has from  " +
            "sys_module_menu  a1  " +
            " where module_levl=#{moduleLevl} and parent_module_id=#{parentModuleId} ")
    public List<ModuleMenu> getModuleMenu2(int moduleLevl,int parentModuleId,int roleId);
    @Delete("delete from sys_role_module where role_id=#{roleId} and module_id=#{moduleId}")
    public void deleteByRoleModule(int roleId, int moduleId);

}
