package pers.legendary.common.core.annotation;

import pers.legendary.common.core.util.RedisUtil;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: Redis缓存注解
 * - 运行时注解
 * - Annotation所修饰的对象范围是方法
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/8/20 23:59
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCache {
    /**
     * Redis key
     */
    String key() default "";

    /**
     * 到期秒数，默认1H，time小于或等于为无限期；
     */
    long expire() default RedisUtil.ONE_HOUR;
}