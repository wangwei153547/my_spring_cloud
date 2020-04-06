package com.wangwei.cloud.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

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
public class ModuleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "module_id", type = IdType.AUTO)
    private Integer moduleId;

    private String moduleName;

    private String moduleDesc;

    private Integer moduleLevl;

    private String isUse;

    private String moduleUrl;

    private Integer parentModuleId;
    private List<ModuleMenu> childModule;

}
