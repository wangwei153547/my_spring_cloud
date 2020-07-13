package com.wangwei.cloud.sys.service.impl;

import com.wangwei.cloud.sys.entity.UserRole;
import com.wangwei.cloud.sys.mapper.UserRoleMapper;
import com.wangwei.cloud.sys.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-03-23
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Override
    public List<Map<String, Object>> getUserRoles(Long userId) {

        return this.baseMapper.getUserRoles(userId) ;
    }
}
