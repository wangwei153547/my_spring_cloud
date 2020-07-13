package com.wangwei.cloud.sys.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.util.List;

import com.wangwei.cloud.config.BaseTable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

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
@TableName("pur_order_headers")
@ApiModel(value="OrderHeaders对象", description="")
public class OrderHeaders extends BaseTable {

    private static final long serialVersionUID = 1L;

    private Long headerId;

    @ApiModelProperty(value = "订单编号")
    private String orderCode;

    @ApiModelProperty(value = "订单标题")
    private String orderName;

    @ApiModelProperty(value = "公司id")
    private Long companyId;

    @ApiModelProperty(value = "公司编号")
    private String companyCode;

    @ApiModelProperty(value = "公司名称")
    private String companyName;

    @ApiModelProperty(value = "供应商id")
    private Long vendorId;

    @ApiModelProperty(value = "供应商编号")
    private String vendorCode;

    @ApiModelProperty(value = "供应商名称")
    private String vendorName;

    @ApiModelProperty(value = "总金额")
    private BigDecimal allMoney;

    @ApiModelProperty(value = "订单行")
    private List<OrderLines> list;


}
