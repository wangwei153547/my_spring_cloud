package com.wangwei.cloud.sys.controller;


import com.wangwei.cloud.sys.entity.ModuleMenu;
import com.wangwei.cloud.sys.service.IRoleModuleService;
import com.wangwei.cloud.util.Results;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
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
 * @since 2020-03-23
 */
@RestController
@RequestMapping("/sys/role-module")
@Api(tags = "菜单模块管理")
public class RoleModuleController {
    @Autowired
    IRoleModuleService service;
    @RequestMapping(value = "/getUserModule",method= RequestMethod.POST)
    @ApiOperation("查询所有")
    public ResponseEntity<List<ModuleMenu>> getUserModule( ){
       // List<ModuleMenu> lists= service.list();

       return Results.success(service.getUserModules("admin",1,0));


    }
}
