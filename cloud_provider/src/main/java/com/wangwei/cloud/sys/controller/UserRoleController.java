package com.wangwei.cloud.sys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangwei.cloud.sys.entity.UserRole;
import com.wangwei.cloud.sys.service.IUserRoleService;
import com.wangwei.cloud.util.MyQueryWrapper;
import com.wangwei.cloud.util.PageRequest;
import com.wangwei.cloud.util.Results;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-03-23
 */
@RestController
@RequestMapping("/sys/user-role")
public class UserRoleController {
    @Autowired
    IUserRoleService service;

    @RequestMapping(value = "/getAll", method = RequestMethod.POST)
    @ApiOperation("查询所有")
    public ResponseEntity<List<Map<String,Object>>> getAll(UserRole record ) throws IllegalAccessException {

        return Results.success(service.getUserRoles(record.getUserId()));
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation("添加用户角色")
    public ResponseEntity<Boolean> insert(UserRole record) throws IllegalAccessException {
        return Results.success(service.save(record));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation("修改用户角色")
    public ResponseEntity<Boolean> update(UserRole record) throws IllegalAccessException {
        return Results.success(service.save(record));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ApiOperation("删除用户角色")
    public ResponseEntity<Boolean> delete(@RequestBody List<UserRole> records) throws IllegalAccessException {
        System.out.println(records);
        for (UserRole record : records) {
            service.removeById(record.getUserRoleId());
        }
        return Results.success(true);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation("保存用户角色")
    public ResponseEntity<Boolean> save(@RequestBody List<UserRole> records) throws IllegalAccessException {


        return Results.success(service.saveOrUpdateBatch(records));
    }
}
