package com.yy.mgb.extend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * PermissionResultDto: 权限传输对象
 *
 * @Author: YangYang
 * @Date: 2021/2/20 8:46
 */
@Setter
@Getter
@ToString
public class UserPermissionDto {

    private Integer id;

    private Integer pid;

    private String name;

    private String value;

    private String icon;

    private String uri;

    private Integer sort;

}
