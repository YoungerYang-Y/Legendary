package com.yy.core.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * ExceptionAspect: 异常切面
 *
 * @Author: YangYang
 * @Date: 2021/2/24 14:48
 */
@Component
@Slf4j
@Aspect
public class ExceptionAspect {

    /**
     * 如果程序出现了异常,则需要拦截,打印异常信息
     */

    @Pointcut("@annotation(com.yy.core.annotation.ExceptionAOP)")
    public void pointcutMethod(){

    }

    /**
     * 前置通知
     * */
    @Before("pointcutMethod()")
    public void before(){

    }

    @AfterThrowing(pointcut = "pointcutMethod()",throwing = "throwable")
    public void afterThrow(JoinPoint joinPoint, Throwable throwable) {

        Class<?> targetClass = joinPoint.getTarget().getClass();
        String methodName = joinPoint.getSignature().getName();
        Class<?> throwClass = throwable.getClass();
        log.error("\n" + targetClass.getName() + "." + methodName + "(): "
                + throwable.getMessage() + " ("+ throwClass.getName() + ")");
    }
}
