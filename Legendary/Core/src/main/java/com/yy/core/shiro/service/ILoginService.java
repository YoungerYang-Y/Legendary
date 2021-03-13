package com.yy.core.shiro.service;

import com.yy.core.shiro.dto.User;

/**
 * ILoginService: Shiro登录服务
 *
 * @Author: YangYang
 * @Date: 2021/3/1 9:38
 */
public interface ILoginService {

    /**
     * 根据用户名获取用户信息
     *
     * @param name 用户名
     * @return 返回用户信息
     */
    User getUser(String name);
}
