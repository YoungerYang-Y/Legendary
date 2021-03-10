package com.yy.api.controller.vo.update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * UpdatePassword:
 *
 * @Author: YangYang
 * @Date: 2021/2/26 11:22
 */
@Setter
@Getter
public class UpdatePassword {

    @ApiModelProperty(value = "用户密码",required = true)
    private String password;
}
