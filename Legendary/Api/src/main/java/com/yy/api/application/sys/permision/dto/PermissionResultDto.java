package com.yy.api.application.sys.permision.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * PermissionResultDto: 权限返回结果传输对象
 *
 * @Author: YangYang
 * @Date: 2021/2/27 21:39
 */
@Setter
@Getter
public class PermissionResultDto {

    private Integer id;

    private Integer pid;

    private String name;

    private String value;

    private String icon;

    private Integer type;

    private String uri;

    private Integer sort;

    private Integer status;

    private Date gmtCreate;

    private Date gmtModified;
}
