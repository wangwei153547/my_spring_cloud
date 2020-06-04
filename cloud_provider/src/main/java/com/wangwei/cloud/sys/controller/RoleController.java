package com.wangwei.cloud.sys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangwei.cloud.sys.entity.Role;
import com.wangwei.cloud.sys.service.IRoleService;
import com.wangwei.cloud.util.MyQueryWrapper;
import com.wangwei.cloud.util.PageRequest;
import com.wangwei.cloud.util.Results;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-03-23
 */
@RestController
@RequestMapping("/sys/role")
@Api(tags = "角色管理controller")
public class RoleController {
    @Autowired
    IRoleService service;

    @RequestMapping(value = "/getAll", method = RequestMethod.POST)
    @ApiOperation("查询所有")
    public ResponseEntity<IPage<Role>> getAll(Role record, PageRequest pageRequest) throws IllegalAccessException {
        System.out.println(pageRequest.toString());
        MyQueryWrapper<Role> queryWrapper2 = new MyQueryWrapper<>();
        queryWrapper2.allEq2(record, pageRequest);
        IPage<Role> page1 = service.page(new Page<>(pageRequest.getPage(), pageRequest.getSize()), queryWrapper2);


        return Results.success(page1);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation("添加角色")
    public ResponseEntity<Boolean> insert(Role record) throws IllegalAccessException {
        return Results.success(service.save(record));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation("修改角色")
    public ResponseEntity<Boolean> update(Role record) throws IllegalAccessException {
        return Results.success(service.save(record));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ApiOperation("删除角色")
    public ResponseEntity<Boolean> delete(@RequestBody List<Role> records) throws IllegalAccessException {
        System.out.println(records);
        for (Role record : records) {
            service.removeById(record.getRoleId());
        }
        return Results.success(true);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation("保存角色")
    public ResponseEntity<Boolean> save(@RequestBody List<Role> records) throws IllegalAccessException {


        return Results.success(service.saveOrUpdateBatch(records));
    }
}
