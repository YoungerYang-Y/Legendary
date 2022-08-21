package pers.legendary.common.core.aspect;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import pers.legendary.common.core.annotation.RedisCache;
import pers.legendary.common.core.util.RedisUtil;

/**
 * Description: Redis缓存切面
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/8/21 0:01
 */
@Slf4j
@Aspect
@EnableAspectJAutoProxy
@Component
public class RedisCacheAspect {

    /**
     * redis 的key格式: 类名:方法名:参数
     */
    private static final String REDIS_KEY_FORMAT = "%s:%s:%s";

    private final RedisUtil redisUtil;

    public RedisCacheAspect(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    /**
     * 拦截所有元注解RedisCache注解的方法
     */
    @Pointcut("@annotation(pers.legendary.common.core.annotation.RedisCache)")
    public void pointcutMethod() {
        log.debug("拦截所有元注解RedisCache注解的方法");
    }

    /**
     * 环绕处理，先从Redis里获取缓存,查询不到，就查询MySQL数据库，
     * 然后再保存到Redis缓存里
     */
    @Around("pointcutMethod()")
    public Object around(ProceedingJoinPoint joinPoint) {

        // 前置：从Redis缓存中获取数据
        long startTime = System.currentTimeMillis();

        RedisCache redisCache = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(RedisCache.class);

        String redisKey = redisCache.key();
        // redis 默认的key格式: 类名:方法名:参数
        if (CharSequenceUtil.isEmpty(redisKey)) {
            // 获取目标方法所在类名
            String target = joinPoint.getTarget().toString();
            String className = target.split("@")[0];

            // 获取目标方法的方法名称
            String methodName = joinPoint.getSignature().getName();

            // 获取目标方法参数
            String params = null;
            Object[] args = joinPoint.getArgs();
            if (ArrayUtil.isNotEmpty(args)) {
                params = ArrayUtil.join(args, "_");
            }
            redisKey = String.format(REDIS_KEY_FORMAT, className, methodName, params);
        }

        Object result = redisUtil.get(redisKey);
        if (result != null) {
            log.debug("Get from redis : { \"key\" : {} ; \"value\" : {} }", redisKey, result);
            return result;
        }
        long endTime = System.currentTimeMillis();
        log.debug("RedisCache Cost:" + (endTime - startTime));
        log.debug("No suitable data in redis.");
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.warn("RedisCache joinPoint.proceed() occur exception, Cause by: {}", e.getMessage(), e);
        }
        log.debug("Get from DB : { \"result\" : {} }", result);
        // 后置：将数据库中查询到数据保存到Redis中
        if (redisUtil.set(redisKey, result, redisCache.expire())) {
            log.debug("Put to redis : { \"key\" : {} ; \"value\" : {} }", redisKey, result);
        }
        return result;
    }
}
