package com.yy.api.application.sys.permision.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * PermissionParamDto: 权限请求参数传输对象
 *
 * @Author: YangYang
 * @Date: 2021/2/27 23:15
 */
@Setter
@Getter
public class PermissionParamDto {

    private Integer pid;

    private String name;

    private String value;

    private String icon;

    private Integer type;

    private String uri;

    private Integer sort;

    private Integer status;

    private Date gmtModified;
}
