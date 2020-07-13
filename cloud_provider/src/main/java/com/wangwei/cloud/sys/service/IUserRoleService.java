package com.wangwei.cloud.sys.service;

import com.wangwei.cloud.sys.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-03-23
 */
public interface IUserRoleService extends IService<UserRole> {
    public List<Map<String,Object>> getUserRoles(Long userId );
}
