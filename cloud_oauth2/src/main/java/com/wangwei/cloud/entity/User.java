package com.wangwei.cloud.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-03-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")

public class User implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;


    private String userName;


    private String userPwd;


    private String userCode;

    private String isUse;
    @TableField(exist=false)
    List<Role> roles;
    @TableField(exist=false)
    Role  defaultRole;

}
