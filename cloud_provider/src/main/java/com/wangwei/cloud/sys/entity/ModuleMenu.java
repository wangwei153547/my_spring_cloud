package com.wangwei.cloud.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import com.wangwei.cloud.config.BaseTable;
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
@TableName("sys_module_menu")
@ApiModel(value="ModuleMenu对象", description="")
public class ModuleMenu  extends BaseTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "module_id", type = IdType.AUTO)
    private Long moduleId;

    private String moduleName;

    private String moduleDesc;

    private Integer moduleLevl;

    private String isUse;

    private String moduleUrl;

    private Long parentModuleId;

    private Integer sortNumber;
    private String moduleCode;
    @TableField(exist=false)
    private    List<ModuleMenu> childModule;
    @TableField(exist=false)
    private String isHas;
    @TableField(exist=false)
    private Long roleId;

}
