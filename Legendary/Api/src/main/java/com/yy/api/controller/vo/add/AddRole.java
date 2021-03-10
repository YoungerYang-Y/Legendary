package com.yy.api.controller.vo.add;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

/**
 * AddRole: 新增角色
 *
 * @Author: YangYang
 * @Date: 2021/2/26 8:55
 */
@Getter
@Setter
public class AddRole {
    @NotEmpty(message = "角色名称不能为空")
    @ApiModelProperty(value = "角色名称")
    private String name;

    @Range(min = 0, max = 1)
    @NotEmpty(message = "启用标志不能为空")
    @ApiModelProperty(value = "是否启用",example = "0")
    private Integer status;
}
