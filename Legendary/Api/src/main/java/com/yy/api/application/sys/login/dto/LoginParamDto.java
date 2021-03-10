package com.yy.api.application.sys.login.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * LoginParamDto:
 *
 * @Author: YangYang
 * @Date: 2021/3/2 15:46
 */
@Setter
@Getter
public class LoginParamDto {

    private String username;
    private String password;
    private boolean rememberMe;
}
