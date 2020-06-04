package com.wangwei.cloud.sys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangwei.cloud.sys.entity.ModuleMenu;
import com.wangwei.cloud.sys.service.IModuleMenuService;
import com.wangwei.cloud.sys.service.IModuleMenuService;
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
 * @since 2020-03-23
 */
@RestController
@RequestMapping("/sys/module-menu")
@Api(tags = "菜单管理controller")
public class ModuleMenuController {
    @Autowired
    IModuleMenuService  service;
    @RequestMapping(value = "/getAll",method= RequestMethod.POST)
    @ApiOperation("查询所有")
    public ResponseEntity<IPage<ModuleMenu>> getAll(ModuleMenu record, PageRequest pageRequest) throws IllegalAccessException {
        System.out.println(pageRequest.toString());
        MyQueryWrapper<ModuleMenu> queryWrapper2 = new MyQueryWrapper<>();
        queryWrapper2.allEq2(record,pageRequest);

        IPage<ModuleMenu> page1=service.page(new Page<>(pageRequest.getPage(),pageRequest.getSize()),queryWrapper2);


        return Results.success(page1 );
    }

    @RequestMapping(value = "/insert",method= RequestMethod.POST)
    @ApiOperation("添加菜单")
    public ResponseEntity<Boolean> insert(ModuleMenu record  ) throws IllegalAccessException {
        return Results.success(service.save(record));
    }
    @RequestMapping(value = "/update",method= RequestMethod.POST)
    @ApiOperation("修改菜单")
    public ResponseEntity<Boolean> update(ModuleMenu record  ) throws IllegalAccessException {
        return Results.success(service.save(record));
    }
    @RequestMapping(value = "/delete",method= RequestMethod.POST)
    @ApiOperation("删除菜单")
    public ResponseEntity<Boolean> delete( @RequestBody List<ModuleMenu> records  ) throws IllegalAccessException {
        System.out.println(records);
        for  (ModuleMenu record :records){
            service.removeById(record.getModuleId());
        }
        return Results.success( true);
    }
    @RequestMapping(value = "/save",method= RequestMethod.POST)
    @ApiOperation("保存菜单")
    public ResponseEntity<Boolean> save( @RequestBody  List<ModuleMenu> records  ) throws IllegalAccessException {

        System.out.println(12345);
        System.out.println(records.toString());

        return Results.success(  service.saveOrUpdateBatch(records));
    }
}
