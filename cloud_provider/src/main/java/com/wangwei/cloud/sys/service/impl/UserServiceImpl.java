package com.wangwei.cloud.sys.service.impl;

import com.wangwei.cloud.sys.entity.User;
import com.wangwei.cloud.sys.mapper.UserMapper;
import com.wangwei.cloud.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-03-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
