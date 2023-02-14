package pers.legendary.common.core.cache;

import cn.hutool.core.util.ArrayUtil;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;
import java.time.Duration;
import java.util.Objects;

/**
 * Description: Redis配置
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/8/21 18:18
 */
@Configurable
@Component
@EnableCaching
public class RedisConfig {

    /**
     * 自定义RedisTemplate，重写RedisAutoConfiguration中的redisTemplate
     */
    @Bean
    @SuppressWarnings("all")
    public RedisTemplate<String, Object> RedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        // String 的序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // JSON序列化配置
        FastJson2JsonRedisSerializer jsonRedisSerializer = new FastJson2JsonRedisSerializer(Object.class);

        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value的序列化方式采用jackson
        template.setValueSerializer(jsonRedisSerializer);
        // hash的value序列化采用jackson
        template.setHashValueSerializer(jsonRedisSerializer);
        template.afterPropertiesSet();

        return template;
    }

    /**
     * <p>SpringBoot配置redis作为默认缓存工具</p>
     * <p>SpringBoot 2.0 以上版本的配置</p>
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate<String, Object> template) {
        RedisCacheConfiguration defaultCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                // 设置key为String
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(template.getStringSerializer()))
                // 设置value 为自动转Json的Object
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(template.getValueSerializer()))
                // 不缓存null
                .disableCachingNullValues()
                // 缓存数据保存1小时
                .entryTtl(Duration.ofHours(1));
        return RedisCacheManager.RedisCacheManagerBuilder
                // Redis 连接工厂
                .fromConnectionFactory(Objects.requireNonNull(template.getConnectionFactory()))
                // 缓存配置
                .cacheDefaults(defaultCacheConfiguration)
                // 配置同步修改或删除 put/evict
                .transactionAware().build();
    }

    /**
     * 自定义key生成器
     */
    @Bean
    public KeyGenerator keyGenerator() {
        return (o, method, params) ->
                // 类名:方法名:参数1_参数2
                o.getClass().getName() + ":" + method.getName() + ":" + ArrayUtil.join(params, "_");
    }
}
