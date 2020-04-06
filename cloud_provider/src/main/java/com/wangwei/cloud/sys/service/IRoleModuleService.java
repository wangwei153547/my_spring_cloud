package com.wangwei.cloud.sys.service;

import com.wangwei.cloud.sys.entity.ModuleMenu;
import com.wangwei.cloud.sys.entity.RoleModule;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-03-23
 */

public interface IRoleModuleService extends IService<RoleModule> {

    public List<ModuleMenu> getUserModules(String user_name, int moduleLevl, int parentModuleId);
}
