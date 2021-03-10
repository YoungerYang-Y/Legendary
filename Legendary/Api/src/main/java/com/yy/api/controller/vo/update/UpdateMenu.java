package com.yy.api.controller.vo.update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;

/**
 * UpdateMenu:
 *
 * @Author: YangYang
 * @Date: 2021/2/26 8:40
 */
@Setter
@Getter
public class UpdateMenu {

    @Min(value = 0)
    @NotEmpty(message = "父类id不能为空")
    @ApiModelProperty(value = "父类id", required = true,example = "0")
    private Integer pid;

    @NotEmpty(message = "菜单名称不能为空")
    @ApiModelProperty(value = "菜单名称", required = true)
    private String name;

    @ApiModelProperty(value = "权限值")
    private String value;

    @Range(min = 0,max = 2)
    @ApiModelProperty(value = "权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）", required = true, allowableValues = "0,1,2")
    private Integer type;

    @ApiModelProperty(value = "前端资源路径")
    private String uri;

    @Min(value = 1)
    @ApiModelProperty(value = "菜单顺序",example = "0")
    private Integer sort;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @Range(min = 0,max = 1)
    @ApiModelProperty(value = "是否启用", allowableValues = "0,1")
    private Integer status;
}
