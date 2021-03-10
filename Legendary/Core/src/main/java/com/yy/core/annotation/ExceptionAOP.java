package com.yy.core.annotation;

import java.lang.annotation.*;

/**
 * ExceptionAOP: 自定义异常拦截注解
 *
 * @Author: YangYang
 * @Date: 2021/2/24 14:59
 */
// Annotation所修饰的对象范围是方法
@Target({ElementType.METHOD,ElementType.TYPE})
// 运行时注解
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ExceptionAOP {
    String values() default "";

    String description() default "自定义异常拦截注解";
}
