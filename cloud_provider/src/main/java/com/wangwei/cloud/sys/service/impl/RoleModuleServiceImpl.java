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
    public List<ModuleMenu> getUserModules(String user_name, int moduleLevl, int parentModuleId){



        List<ModuleMenu> lists= this.baseMapper.getModuleMenu(user_name,moduleLevl,parentModuleId);
         for(ModuleMenu moduleMenu:lists){
             List<ModuleMenu> lists2=getUserModules(  user_name, moduleMenu.getModuleLevl()+1, moduleMenu.getModuleId());
             moduleMenu.setChildModule(lists2);
         }
        return lists;
    }
}
