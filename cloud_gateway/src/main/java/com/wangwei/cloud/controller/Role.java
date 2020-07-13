package com.wangwei.cloud.controller;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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


public class Role implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long roleId;

    private String roleName;

    private String isUse;
    private String roleCode;

    private String  isDefault;
}
