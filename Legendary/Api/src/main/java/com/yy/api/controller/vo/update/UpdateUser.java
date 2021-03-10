package com.yy.api.controller.vo.update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import java.util.Date;

/**
 * UpdateUser:
 *
 * @Author: YangYang
 * @Date: 2021/2/26 11:29
 */
@Setter
@Getter
public class UpdateUser {

    @NotEmpty(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @NotEmpty(message = "密码不能为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @Email
    @ApiModelProperty(value = "邮箱")
    private String email;

    @Range(min = 0,max = 2)
    @ApiModelProperty(value = "性别：0->未知；1->男；2->女", required = true, allowableValues = "0,1,2")
    private Integer gender;

    @ApiModelProperty(value = "生日")
    private Date birthday;

    @ApiModelProperty(value = "所在城市")
    private String city;

    @ApiModelProperty(value = "头像")
    private String icon;

    @Range(min = 0,max = 1)
    @ApiModelProperty(value = "帐号启用状态：0->禁用；1->启用", required = true, allowableValues = "0,1,2")
    private Integer status;
}
