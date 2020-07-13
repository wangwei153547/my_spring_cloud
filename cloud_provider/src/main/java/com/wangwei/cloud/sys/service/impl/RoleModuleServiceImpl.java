package com.wangwei.cloud.sys.service.impl;

import com.wangwei.cloud.sys.entity.ModuleMenu;
import com.wangwei.cloud.sys.entity.RoleModule;
import com.wangwei.cloud.sys.mapper.RoleModuleMapper;
import com.wangwei.cloud.sys.service.IRoleModuleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-03-23
 */
@Service
public class RoleModuleServiceImpl extends ServiceImpl<RoleModuleMapper, RoleModule> implements IRoleModuleService {

    @Override
    public List<ModuleMenu> getUserModules(String roleCode, Integer moduleLevl, Long parentModuleId){



        List<ModuleMenu> lists= this.baseMapper.getModuleMenu(roleCode,moduleLevl,parentModuleId);
        System.out.println(lists.toString());
         for(ModuleMenu moduleMenu:lists){
             List<ModuleMenu> lists2=getUserModules(  roleCode, moduleMenu.getModuleLevl()+1, moduleMenu.getModuleId());
             moduleMenu.setChildModule(lists2);
         }
        return lists;
    }
    @Override
    public List<ModuleMenu> getUserModules(int moduleLevl, Long parentModuleId){


        System.out.println(333333);
        List<ModuleMenu> lists= this.baseMapper.getModuleMenu3(moduleLevl,parentModuleId);

        for(ModuleMenu moduleMenu:lists){
            List<ModuleMenu> lists2=getUserModules(  moduleMenu.getModuleLevl()+1, moduleMenu.getModuleId());
            moduleMenu.setChildModule(lists2);
        }
        return lists;
    }
    @Override
    public List<ModuleMenu> getUserModules( int moduleLevl, Long parentModuleId,Long roleId){



        List<ModuleMenu> lists= this.baseMapper.getModuleMenu2(moduleLevl,parentModuleId,roleId);
        for(ModuleMenu moduleMenu:lists){
            List<ModuleMenu> lists2=getUserModules(moduleMenu.getModuleLevl()+1, moduleMenu.getModuleId(),roleId);
            moduleMenu.setChildModule(lists2);
        }
        return lists;
    }

    @Override
    public List<ModuleMenu> getUserModulesByUserCode(String roleCode,String moduleName ) {
        return this.baseMapper.getUserModulesByUserCode(roleCode,  moduleName );
    }

    @Override
    public void deleteByRoleModule(Long roleId, Long moduleId){
        this.baseMapper.deleteByRoleModule( roleId,  moduleId);
    }
}
