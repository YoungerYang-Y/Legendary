package com.yy.api.controller.vo.result;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;

/**
 * RoleResult:
 *
 * @Author: YangYang
 * @Date: 2021/2/26 11:07
 */
@Setter
@Getter
public class RoleResult {

    @ApiModelProperty(value = "主键id",example = "0")
    private Integer id;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "状态：0->禁用；1->启用",example = "0")
    private Integer status;
}
