package com.wangwei.cloud.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role_module")
@ApiModel(value="RoleModule对象", description="")
public class RoleModule implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "role_module_id", type = IdType.AUTO)
    private Long roleModuleId;

    private Long roleId;

    private Long moduleId;

    private String isUse;


}
