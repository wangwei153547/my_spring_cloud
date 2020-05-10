package com.wangwei.cloud.util;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("Page_Request")
@ApiModel(value="page对象", description="")
public class PageRequest  implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "页数")
    private int page;
    @ApiModelProperty(value = "行数")
    private int size;
    @ApiModelProperty(value = "排序")
    private List<Sort> sorts;
    @ApiModelProperty(value = "顺序")
    private String direction;
    @ApiModelProperty(value = "排序字段")
    private String  order;
}
