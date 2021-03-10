package com.yy.api.application.sys.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * UserParam: 用户信息传输对象
 *
 * @Author: YangYang
 * @Date: 2021/2/22 11:53
 */
@Getter
@Setter
@ToString
public class UserParam {

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
