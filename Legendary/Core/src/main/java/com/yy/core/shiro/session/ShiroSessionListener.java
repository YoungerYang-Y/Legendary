package com.yy.core.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ShiroSessionListener: 自定义Shiro 会话监听
 *
 * @Author: YangYang
 * @Date: 2021/3/2 20:52
 */
public class ShiroSessionListener implements SessionListener {

    /**
     * 统计在线人数
     * juc包下线程安全自增
     */
    private final AtomicInteger sessionCount = new AtomicInteger(0);

    @Override
    public void onStart(Session session) {

        // 人数+1 开启
        sessionCount.incrementAndGet();
    }

    @Override
    public void onStop(Session session) {

        // 人数-1 关闭
        sessionCount.decrementAndGet();
    }

    @Override
    public void onExpiration(Session session) {

        // 人数-1 过期
        sessionCount.decrementAndGet();
    }

    public AtomicInteger getSessionCount(){
        return this.sessionCount;
    }
}
