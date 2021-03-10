package com.yy.api.application.sys.login;

import com.yy.api.application.sys.login.dto.LoginParamDto;

/**
 * LoginManage:
 *
 * @Author: YangYang
 * @Date: 2021/3/2 15:44
 */
public interface ILoginManage {

    /**
     * 登录
     *
     * @param loginParamDto 用户登录信息
     * @return 返回执行结果
     */
    boolean login(LoginParamDto loginParamDto);

    /**
     * 登出
     *
     * @return 返回执行结果
     */
    boolean logout();
}
