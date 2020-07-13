package com.wangwei.cloud.sys.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
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
 * @since 2020-06-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("pur_order_lines")
@ApiModel(value="OrderLines对象", description="")
public class OrderLines extends BaseTable {

    private static final long serialVersionUID = 1L;

    private Long lineId;

    @ApiModelProperty(value = "行号")
    private Integer lineNum;

    @ApiModelProperty(value = "物料id")
    private Long itemId;

    @ApiModelProperty(value = "物料编号")
    private String itemCode;

    @ApiModelProperty(value = "物料名称")
    private String itemName;

    @ApiModelProperty(value = "数量")
    private Integer quantity;

    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;

    @ApiModelProperty(value = "行总金额")
    private BigDecimal allUintPrice;




}
