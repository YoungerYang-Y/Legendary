package com.yy.core.shiro.api;

import com.yy.core.shiro.dto.User;

/**
 * LoginApi:
 *
 * @Author: YangYang
 * @Date: 2021/3/2 15:51
 */
public interface LoginApi {
    /**
     * Shiro登录校验
     *
     * @param user 登录用户信息
     * @return 返回执行结果
     * @exception RuntimeException 运行时异常
     */
    boolean login(User user) throws RuntimeException;

    /**
     * Shiro登出
     *
     * @return 返回执行结果
     */
    boolean logout();
}
