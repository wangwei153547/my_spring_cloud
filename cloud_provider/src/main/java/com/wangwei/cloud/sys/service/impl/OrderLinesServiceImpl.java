package com.wangwei.cloud.sys.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangwei.cloud.sys.entity.OrderLines;
import com.wangwei.cloud.sys.mapper.OrderLinesMapper;
import com.wangwei.cloud.sys.service.IOrderLinesService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-06-27
 */
@Service
public class OrderLinesServiceImpl extends ServiceImpl<OrderLinesMapper, OrderLines> implements IOrderLinesService {

}
