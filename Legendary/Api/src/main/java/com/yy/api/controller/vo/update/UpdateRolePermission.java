package com.yy.api.controller.vo.update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * UpdateRolePermission:
 *
 * @Author: YangYang
 * @Date: 2021/2/26 14:59
 */
@Setter
@Getter
public class UpdateRolePermission {

    @NotEmpty(message = "角色id不能为空")
    @ApiModelProperty(value = "角色id",required = true,example = "0")
    private Integer roleId;

    @NotEmpty(message = "权限id不能为空")
    @ApiModelProperty(value = "权限id",required = true,example = "0")
    private Integer permissionId;
}
