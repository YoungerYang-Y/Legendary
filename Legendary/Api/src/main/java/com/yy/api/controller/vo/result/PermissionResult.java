package com.yy.api.controller.vo.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * PermissionResult: 返回给前端的权限VO
 *
 * @Author: YangYang
 * @Date: 2021/2/25 17:08
 */
@Getter
@Setter
@ToString
public class PermissionResult {

    @ApiModelProperty(value = "主键id", example = "0")
    private Integer id;

    @ApiModelProperty(value = "父类id", example = "0")
    private Integer pid;

    @ApiModelProperty(value = "权限名称")
    private String name;

    @ApiModelProperty(value = "权限值")
    private String value;

    @ApiModelProperty(value = "权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）",example = "0")
    private Integer type;

    @ApiModelProperty(value = "前端资源路径")
    private String uri;

    @ApiModelProperty(value = "权限顺序",example = "0")
    private Integer sort;

    @ApiModelProperty(value = "权限图标")
    private String icon;

    @ApiModelProperty(value = "是否启用",example = "0")
    private Integer status;
}
