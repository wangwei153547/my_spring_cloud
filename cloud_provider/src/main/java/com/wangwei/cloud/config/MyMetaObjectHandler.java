package com.wangwei.cloud.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.wangwei.cloud.interceptor.UserThreadLocal;
import com.wangwei.cloud.sys.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        User user= UserThreadLocal.get();
        this.fillStrategy(metaObject, "creationDate",  new Date()); // 起始版本 3.3.0(推荐使用)
        this.fillStrategy(metaObject, "createdBy" , user.getUserId()); // 起始版本 3.3.0(推荐使用)

        this.fillStrategy(metaObject, "lastUpdateDate", new Date());
        this.fillStrategy(metaObject, "lastUpdatedBy", user.getUserId());
        //  this.fillStrategy(metaObject, "createTime", LocalDateTime.now()); // 也可以使用(3.3.0 该方法有bug请升级到之后的版本如`3.3.1.8-SNAPSHOT`)
        /* 上面选其一使用,下面的已过时(注意 strictInsertFill 有多个方法,详细查看源码) */
        //this.setFieldValByName("operator", "Jerry", metaObject);
        //this.setInsertFieldValByName("operator", "Jerry", metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        User user= UserThreadLocal.get();
        SimpleDateFormat spf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(spf.format( new Date()));
       // this.strictInsertFill(metaObject, "last_update_date", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
        this.fillStrategy(metaObject, "lastUpdateDate",  new Date());
        this.fillStrategy(metaObject, "lastUpdatedBy", user.getUserId());
      //  this.strictInsertFill(metaObject, "last_updated_by",Integer.class, user.getUserId()); // 起始版本 3.3.0(推荐使用)

        // this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
       //  this.fillStrategy(metaObject, "updateTime", LocalDateTime.now()); // 也可以使用(3.3.0 该方法有bug请升级到之后的版本如`3.3.1.8-SNAPSHOT`)
        /* 上面选其一使用,下面的已过时(注意 strictUpdateFill 有多个方法,详细查看源码) */
        //this.setFieldValByName("operator", "Tom", metaObject);
        //this.setUpdateFieldValByName("operator", "Tom", metaObject);
    }
}