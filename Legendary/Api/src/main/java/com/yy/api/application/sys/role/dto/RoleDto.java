package com.yy.api.application.sys.role.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * UserRoleDto: 角色传输对象
 *
 * @Author: YangYang
 * @Date: 2021/2/19 21:23
 */
@Setter
@Getter
@ToString
public class RoleDto implements Serializable {

    /**
     * 角色id
     */
    private Integer id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色状态
     */
    private Integer status;
}
