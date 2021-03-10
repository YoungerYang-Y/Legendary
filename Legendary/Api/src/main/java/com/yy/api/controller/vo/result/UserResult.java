package com.yy.api.controller.vo.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * UserResult: 用户信息返回结果
 *
 * @Author: YangYang
 * @Date: 2021/2/26 11:45
 */
@Setter
@Getter
public class UserResult {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "性别：0->未知；1->男；2->女",required = true,allowableValues = "0,1,2")
    private Integer gender;

    @ApiModelProperty(value = "生日")
    private Date birthday;

    @ApiModelProperty(value = "所在城市")
    private String city;

    @ApiModelProperty(value = "头像")
    private String icon;

    @ApiModelProperty(value = "帐号启用状态：0->禁用；1->启用",allowableValues = "0,1,2")
    private Integer status;
}
