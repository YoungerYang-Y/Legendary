package com.yy.api.controller.vo.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * LoginParam:
 *
 * @Author: YangYang
 * @Date: 2021/3/2 15:55
 */
@Setter
@Getter
@ToString
public class LoginParam {
    @NotEmpty(message = "账户名称不能为空")
    @ApiModelProperty(value = "账户",required = true)
    private String username;
    @NotEmpty(message = "密码名称不能为空")
    @ApiModelProperty(value = "密码",required = true)
    private String password;
    @NotNull
    @ApiModelProperty(value = "记住我")
    private boolean rememberMe;
}
