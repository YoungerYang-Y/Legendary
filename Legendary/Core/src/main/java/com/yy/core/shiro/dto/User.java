package com.yy.core.shiro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

/**
 * User:
 *
 * @Author: YangYang
 * @Date: 2021/3/2 11:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private Integer id;
    private String username;
    private String password;
    private boolean rememberMe;
    private Integer status;
    private Set<Role> roles;
}
