package com.wangwei.cloud.sys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangwei.cloud.sys.entity.User;
import com.wangwei.cloud.sys.service.IUserService;
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
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-03-09
 */
@RestController
@RequestMapping("/sys/user")
@Api(tags = "用户管理controller")
public class UserController {
    @Autowired
    IUserService  service;
    @RequestMapping(value = "/getAll",method= RequestMethod.POST)
    @ApiOperation("查询所有")
    public ResponseEntity<IPage<User>> getSysUsers(  User user, PageRequest pageRequest) throws IllegalAccessException {

        MyQueryWrapper<User> queryWrapper2 = new MyQueryWrapper<>();
        queryWrapper2.allEq2(user,pageRequest);

        IPage<User> page1=service.page(new Page<>(pageRequest.getPage(),pageRequest.getSize()),queryWrapper2);


        return Results.success(page1 );
    }
    @RequestMapping(value = "/insert",method= RequestMethod.POST)
    @ApiOperation("添加用户")
    public ResponseEntity<Boolean> insertUsers(User user  ) throws IllegalAccessException {
       return Results.success(service.save(user));
    }
    @RequestMapping(value = "/updateUser",method= RequestMethod.POST)
    @ApiOperation("修改用户")
    public ResponseEntity<Boolean> updateUsers(User user  ) throws IllegalAccessException {
        return Results.success(service.save(user));
    }
    @RequestMapping(value = "/delete",method= RequestMethod.POST)
    @ApiOperation("删除用户")
    public ResponseEntity<Boolean> deleteUsers( @RequestBody  List<User> users  ) throws IllegalAccessException {
        System.out.println(users);
        for  (User user :users){
            service.removeById(user.getUserId());
        }
        return Results.success( true);
    }
    @RequestMapping(value = "/save",method= RequestMethod.POST)
    @ApiOperation("保存用户")
    public ResponseEntity<Boolean> saveUsers( @RequestBody  List<User> users  ) throws IllegalAccessException {


    return Results.success(  service.saveOrUpdateBatch(users));
    }


}
