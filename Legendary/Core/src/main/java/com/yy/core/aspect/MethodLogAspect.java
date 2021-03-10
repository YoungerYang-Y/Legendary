package com.yy.core.aspect;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * MethodLogAspect: 方法日志切面
 *
 * @Author: YangYang
 * @Date: 2021/3/10 14:47
 */
@Aspect
@Component
@Slf4j
public class MethodLogAspect {

    @Pointcut("@annotation(com.yy.core.annotation.MethodLog)")
    public void pointcutMethod(){

    }

    @Before(value = "pointcutMethod()")
    public void before(JoinPoint joinPoint){

        ServletRequestAttributes requestAttributes =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = requestAttributes.getRequest();

        log.debug("---------------------------请求内容-----------------------------");
        try{
            String url = httpServletRequest.getRequestURI();
            log.debug("请求地址：" + url);
            String way = httpServletRequest.getMethod();
            log.debug("请求方式：" + way);
            String method = joinPoint.getSignature().toString();
            log.debug("请求类方法：" + method);
            String params = JSONUtil.toJsonStr(joinPoint.getArgs());
            log.debug("请求类方法参数：" + params);

        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        log.debug("---------------------------请求内容-----------------------------");
    }

    @AfterReturning(returning = "obj", pointcut = "pointcutMethod()")
    public void afterReturn(Object obj){

        log.debug("---------------------------返回结果-----------------------------");
        try{
            String result = JSONUtil.toJsonStr(obj);
            log.debug(result);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        log.debug("---------------------------返回结果-----------------------------");
    }
}
