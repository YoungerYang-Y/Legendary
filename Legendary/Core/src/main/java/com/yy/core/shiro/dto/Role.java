package com.yy.core.shiro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Role:
 *
 * @Author: YangYang
 * @Date: 2021/3/2 11:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    private Integer id;
    private String name;
    private Set<Permission> permissions;
}
