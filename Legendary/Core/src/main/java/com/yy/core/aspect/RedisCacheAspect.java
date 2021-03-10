package com.yy.core.aspect;

import com.yy.core.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.regex.Pattern;

/**
 * Redis缓存切面
 * @author Administrator
 */
@Component
@Aspect
@Slf4j
public class RedisCacheAspect {

    /**
     * redis 存活时长  秒
     */
    private static final Integer TIME_OUT = 3600 ;
    /**
     * 获取缓存验证
     */
    private static final Pattern GET_CACHE_PATTERN = Pattern.compile("^((get)|(query)|(select)|(list)).*$");

    /**
     * 更新缓存验证
     */
    private static final Pattern SET_CACHE_PATTERN = Pattern.compile("^((add)|(insert)|(save)|(batchInsert)|(batchUpdate)|(update)|(delete)|(change)|(remove)).*$");



    private RedisUtil redisUtil;

    public RedisCacheAspect(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    /**
     * 拦截所有元注解RedisCache注解的方法
     * 切入指定方法：@Pointcut("execution(* com.yang.poseidon.domain.service.PmsProductService.getById(..))")
     */
    @Pointcut("@annotation(com.yy.core.annotation.RedisCache)")
    public void pointcutMethod(){

    }

    /**
     * 前置通知
     * */
    @Before("pointcutMethod()")
    public void before(){

    }

    /**
     * 后置通知
     * */
    @After("pointcutMethod()")
    public void doAfter(){

    }

    /**
     * 环绕处理，先从Redis里获取缓存,查询不到，就查询MySQL数据库，
     * 然后再保存到Redis缓存里
     * @param joinPoint
     * @return
     */
    @Around("pointcutMethod()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        // 前置：从Redis缓存中获取数据
        long startTime = System.currentTimeMillis();

        // 获取目标方法所在类
        String target = joinPoint.getTarget().toString();
        String className = target.split("@")[0];

        // 获取目标方法的方法名称
        String methodName = joinPoint.getSignature().getName();

        // 获取目标方法参数
        String appId = null;
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0){
            appId = String.valueOf(args[0]);
        }

        // redis 的key格式: 参数_类名:方法名
        String redisKey = appId + "_" + className + ":" + methodName;

        // 如果是查询接口 则获取缓存
        if(GET_CACHE_PATTERN.matcher(methodName).matches()){
            Object result = redisUtil.get(redisKey);
            if (result != null){
                log.info("<==   Get key:" + redisKey);
                log.info("<==   Get value:" + result.toString());
                return result;
            }
            long endTime = System.currentTimeMillis();
            log.info("<==   RedisAOP Cost：【" + (endTime - startTime) + "ms】");
            result = joinPoint.proceed();
            // 后置：将数据库中查询到数据保存到Redis中
            if (redisUtil.set(redisKey, result, TIME_OUT)){
                log.info("==>   Save key:" + redisKey);
                log.info("==>   Save value:" + result.toString());
            }
            return result;

        }
        // 如果是更改或新增数据 则填充缓存
        else if(SET_CACHE_PATTERN.matcher(methodName).matches()){
            Set<String> keys = redisUtil.keys("*"+className+"*");
            redisUtil.del(keys);
            log.warn("执行方法 : [ "+methodName+" ] :  清除 key 包含 [ "+className+" ] 的缓存数据");
            log.warn("AOP 清除缓存 >>>> end 耗时：" + (System.currentTimeMillis() - startTime) + "ms.");
        }
        return joinPoint.proceed();
    }

//    /**
//     * @Title: getObject
//     * @Description: 使用key获取数据  不存在则查询添加
//     * @param beginTime : 切面开始时间
//     * @param joinPoint : 切面对象
//     * @param key : 获取redis数据的key值
//     * @return java.lang.Object
//     **/
//    private Object getObject(long beginTime,ProceedingJoinPoint joinPoint,String key) throws Throwable {
//        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
//        boolean hasKey = redisTemplate.hasKey(key);
//        Object object = null;
//        if(hasKey){
//            // 缓存中获取到数据，直接返回。
//            object = operations.get(key);
//            log.warn("从缓存中获取到 key 为 ["+key+" ] : 的数据 >>>> " + object.toString());
//            log.warn("AOP 获取缓存 >>>> end 耗时：" + (System.currentTimeMillis() - beginTime) + "ms.");
//            return object;
//        }
//        if(object == null) {
//            // 缓存中没有数据，调用原始方法查询数据库
//            object = joinPoint.proceed();
//            // 设置超时时间30分钟
//            operations.set(key, object, TIME_OUT, TimeUnit.MINUTES);
//            log.warn("向 Redis 添加 key 为 ["+key+" ] , 存活时长为 "+TIME_OUT+" min 的数据 >>>> " + object.toString());
//            log.warn("AOP 添加缓存 >>>> end 耗时：" + (System.currentTimeMillis() - beginTime) + "ms.");
//        }
//        return object;
//    }

}
