package com.wangwei.cloud.config;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.util.Date;

public class BaseTable {
    @TableField( fill = FieldFill.INSERT)
    private Date creationDate;
    @TableField( fill = FieldFill.INSERT)
    private Long createdBy;
    @TableField( fill = FieldFill.INSERT_UPDATE)
    private Date lastUpdateDate;
    @TableField( fill = FieldFill.INSERT_UPDATE)
    private Long lastUpdatedBy;
}
