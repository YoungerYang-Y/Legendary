package com.yy.core.annotation;

import java.lang.annotation.*;

/**
 * Redis缓存注解
 * 支持方法
 * @author Administrator
 */
// Annotation所修饰的对象范围是方法
@Target({ElementType.METHOD,ElementType.TYPE})
// 运行时注解
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RedisCache {

    /**
     * 到期秒数，默认1H
     *
     * @return 到期秒数
     */
    int expireSeconds() default 3600;
}
