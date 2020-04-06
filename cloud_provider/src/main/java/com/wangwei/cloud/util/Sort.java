package com.wangwei.cloud.util;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sort")
@ApiModel(value="sort对象", description="")
public class Sort  implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "排序顺序")
    private String direction;
    @ApiModelProperty(value = "排序字段")
    private String order;


}
