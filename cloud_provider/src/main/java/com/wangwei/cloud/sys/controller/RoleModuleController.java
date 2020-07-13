package com.wangwei.cloud.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wangwei.cloud.sys.entity.ModuleMenu;
import com.wangwei.cloud.sys.entity.RoleModule;
import com.wangwei.cloud.sys.entity.User;
import com.wangwei.cloud.sys.service.IRoleModuleService;
import com.wangwei.cloud.util.MyQueryWrapper;
import com.wangwei.cloud.util.Results;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @RequestMapping(value = "/getUserModule",method= RequestMethod.POST,produces="application/json;charset=utf-8")
    @ApiOperation("查询角色模块")
    public  List<ModuleMenu>  getUserModule( ){
       // List<ModuleMenu> lists= service.list();
      //  System.out.println(service.getUserModules("admin",1,0).toString());
        List<ModuleMenu> lists=service.getUserModules("ALL",1,0L);
        System.out.println(lists.toString());
       return  lists;


    }
    @RequestMapping(value = "/getModuleByRoleCode",method= RequestMethod.POST,produces="application/json;charset=utf-8")
    @ApiOperation("查询三级角色模块")
    public  List<ModuleMenu>  getModuleByRoleCode( @RequestParam String roleCode,@RequestParam String moduleName){
        // List<ModuleMenu> lists= service.list();
        //  System.out.println(service.getUserModules("admin",1,0).toString());
        List<ModuleMenu> lists=service.getUserModulesByUserCode(roleCode ,moduleName);
        System.out.println(lists.toString());
        return  lists;


    }

    @RequestMapping(value = "/getUserModule2",method= RequestMethod.POST)
    @ApiOperation("查询所有")
    public ResponseEntity<List<ModuleMenu>> getUserModule2( ){
        // List<ModuleMenu> lists= service.list();
        return Results.success(service.getUserModules( 1,0L));


    }
    @RequestMapping(value = "/getUserModule3",method= RequestMethod.POST)
    @ApiOperation("查询所有")
    public ResponseEntity<List<ModuleMenu>> getUserModule3( Long roleId){

        return Results.success(service.getUserModules( 1,0L,roleId));


    }
    @RequestMapping(value = "/save",method= RequestMethod.POST)
    @ApiOperation("查询所有")
    public ResponseEntity<Boolean> save(@RequestBody List<ModuleMenu> list) throws IllegalAccessException {
        List<RoleModule> list2=new ArrayList<RoleModule>();
        for( ModuleMenu  record:list){


            if ("Y".equals(record.getIsHas())) {
                System.out.println("wddssdddddddddddaaa");
                RoleModule record2 = new RoleModule();
                record2.setRoleId(record.getRoleId());
                record2.setModuleId(record.getModuleId());
                record2.setIsUse("Y");
                System.out.println(1234);
                System.out.println(record2.toString());
                MyQueryWrapper<RoleModule> queryWrapper2 = new MyQueryWrapper<>();
                queryWrapper2.allEq2(record2);
               List<RoleModule> record3=service.list(queryWrapper2)  ;
                if(record3.size()==0) {
                    list2.add(record2);
                }
               }else {

                service.deleteByRoleModule(record.getRoleId(),record.getModuleId());
            }

        }
        service.saveBatch(list2);
        return Results.success(true);


    }
}
