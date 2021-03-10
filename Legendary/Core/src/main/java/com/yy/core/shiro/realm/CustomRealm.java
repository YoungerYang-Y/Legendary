package com.yy.core.shiro.realm;

import cn.hutool.core.util.StrUtil;
import com.yy.core.common.CommonConst;
import com.yy.core.shiro.dto.Permission;
import com.yy.core.shiro.dto.Role;
import com.yy.core.shiro.dto.User;
import com.yy.core.shiro.service.ILoginService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * CustomRealm: 自定义验证方式
 *
 * @Author: YangYang
 * @Date: 2021/3/1 9:21
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private ILoginService loginService;

    /**
     * 权限配置类
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        // 获取登录用户名
        String name = (String) principalCollection.getPrimaryPrincipal();
        // 查询用户信息
        User user = loginService.getUserByName(name);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role : user.getRoles()) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getName());
            //添加权限
            for (Permission permission : role.getPermissions()) {
                simpleAuthorizationInfo.addStringPermission(permission.getValue());
            }
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 登录认证配置
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        if (StrUtil.isEmptyIfStr(authenticationToken.getPrincipal())) {
            return null;
        }
        //获取用户信息
        String username = authenticationToken.getPrincipal().toString();
        String password = new String((char[]) authenticationToken.getCredentials());
        if (username == null) {
            throw new AccountException("Null username are not allowed by this realm.");
        }
        if (password == null) {
            throw new UnknownAccountException("No account found for user [" + username + "]");
        }

        User user = loginService.getUserByName(username);
        if (user == null) {
            //这里返回后会报出对应异常
            throw new UnknownAccountException("用户名或密码错误！");
        }
        if (!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("用户名或密码错误！");
        }
        if (CommonConst.STATUS_DISABLE.equals(user.getStatus())) {
            throw new LockedAccountException("账号已被锁定,请联系管理员！");
        }

        //这里验证authenticationToken和simpleAuthenticationInfo的信息
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, getName());
        return info;
    }

    /**
     * 重写方法,清除当前用户的的 授权缓存
     * @param principals
     */
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 重写方法，清除当前用户的 认证缓存
     * @param principals
     */
    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    /**
     * 自定义方法：清除所有 授权缓存
     */
    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    /**
     * 自定义方法：清除所有 认证缓存
     */
    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    /**
     * 自定义方法：清除所有的  认证缓存  和 授权缓存
     */
    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
}
