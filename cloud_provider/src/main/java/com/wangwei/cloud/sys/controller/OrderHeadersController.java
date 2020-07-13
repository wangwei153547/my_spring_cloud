package com.wangwei.cloud.sys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


import com.wangwei.cloud.sys.entity.OrderHeaders;

import com.wangwei.cloud.sys.service.IOrderHeadersService;
import com.wangwei.cloud.util.MyQueryWrapper;
import com.wangwei.cloud.util.PageRequest;
import com.wangwei.cloud.util.Results;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-06-27
 */
@RestController
@RequestMapping("/pur/order-headers")
@Api(tags = "订单管理controller")
public class OrderHeadersController {
   @Autowired
   IOrderHeadersService service;
    @RequestMapping(value = "/getAll",method= RequestMethod.POST)
    @ApiOperation("查询所有")
    public ResponseEntity<IPage<OrderHeaders>> getAll(OrderHeaders record, PageRequest pageRequest) throws IllegalAccessException {
        System.out.println(pageRequest.toString());
        MyQueryWrapper<OrderHeaders> queryWrapper2 = new MyQueryWrapper<>();
        queryWrapper2.allEq2(record,pageRequest);

        IPage<OrderHeaders> page1=service.page(new Page<>(pageRequest.getPage(),pageRequest.getSize()),queryWrapper2);
        return Results.success(page1 );
    }
    @RequestMapping(value = "/save",method= RequestMethod.POST)
    @ApiOperation("保存订单")
    public ResponseEntity<Boolean> saveUsers( @RequestBody @Validated List<OrderHeaders> records  ) throws IllegalAccessException {
      return Results.success(  service.saveOrUpdateBatch(records));
    }
}
