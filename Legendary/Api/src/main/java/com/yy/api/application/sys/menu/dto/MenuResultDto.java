package com.yy.api.application.sys.menu.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * MenuResultDto:
 *
 * @Author: YangYang
 * @Date: 2021/2/26 22:50
 */
@Setter
@Getter
@ToString
public class MenuResultDto {

    private Integer id;

    private Integer pid;

    private String name;

    private String value;

    private String icon;

    private String uri;

    private Integer sort;

    private Integer status;
}
