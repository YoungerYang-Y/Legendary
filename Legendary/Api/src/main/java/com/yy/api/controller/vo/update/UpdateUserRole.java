package com.yy.api.controller.vo.update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * UpdateUserRole:
 *
 * @Author: YangYang
 * @Date: 2021/2/26 11:58
 */
@Setter
@Getter
public class UpdateUserRole {

    @NotEmpty(message = "用户id不能为空")
    @ApiModelProperty(value = "用户id",required = true,example = "0")
    private Integer userId;

    @NotEmpty(message = "角色id不能为空")
    @ApiModelProperty(value = "角色id",required = true,example = "0")
    private Integer roleId;
}
