package com.yy.core.annotation;

import java.lang.annotation.*;

/**
 * MethodLog: 方法的参数与返回结果打印日志
 *
 * @Author: YangYang
 * @Date: 2021/3/10 14:45
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MethodLog {
}
