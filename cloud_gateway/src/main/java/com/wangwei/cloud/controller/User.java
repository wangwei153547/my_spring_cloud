package com.wangwei.cloud.controller;



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


public class User implements Serializable {

    private static final long serialVersionUID = 1L;


     private Long userId;


    private String userName;


    private String userPwd;


    private String userCode;

    private String isUse;
    List<Role> roles;

}
