package com.yy.api.application.sys.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * LoginParamDto: 用户传输对象
 *
 * @Author: YangYang
 * @Date: 2021/2/22 17:01
 */
@Setter
@Getter
public class UserDto {

    private Integer id;

    private String username;

    private String password;

    private String nickname;

    private String phone;

    private String email;

    private Integer gender;

    private Date birthday;

    private String city;

    private String icon;

    private Integer status;
}
