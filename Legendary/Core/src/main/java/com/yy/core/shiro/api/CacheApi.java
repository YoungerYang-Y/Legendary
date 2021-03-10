package com.yy.core.shiro.api;

/**
 * CacheApi:
 *
 * @Author: YangYang
 * @Date: 2021/3/2 21:31
 */
public interface CacheApi {

    /**
     * Shiro清楚所有缓存
     *
     * @return 返回执行结果
     */
    boolean clearAllCache();
}
