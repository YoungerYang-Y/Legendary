package com.yy.api.controller.vo.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * MenuTreeResult: 返回给前端的菜单树形结构VO
 *
 * @Author: YangYang
 * @Date: 2021/2/25 17:07
 */
@Getter
@Setter
public class MenuTreeResult {

    @ApiModelProperty(value = "主键id",example = "0")
    private Integer id;

    @ApiModelProperty(value = "父类id",example = "0")
    private Integer pid;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "权限值")
    private String value;

    @ApiModelProperty(value = "权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）",example = "0")
    private Integer type;

    @ApiModelProperty(value = "前端资源路径")
    private String uri;

    @ApiModelProperty(value = "菜单顺序",example = "0")
    private Integer sort;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

}
