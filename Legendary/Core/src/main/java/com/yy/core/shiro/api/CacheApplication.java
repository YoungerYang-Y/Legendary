package com.yy.core.shiro.api;

import com.yy.core.shiro.realm.CustomRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.stereotype.Component;

/**
 * CacheApplication:
 *
 * @Author: YangYang
 * @Date: 2021/3/2 21:33
 */
@Component
public class CacheApplication implements CacheApi {
    @Override
    public boolean clearAllCache() {

        //添加成功之后 清除缓存
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        CustomRealm shiroRealm = (CustomRealm) securityManager.getRealms().iterator().next();
        //清除权限 相关的缓存
        shiroRealm.clearAllCache();

        return true;
    }
}
