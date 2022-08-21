package pers.legendary.common.core.config;

import cn.hutool.core.util.StrUtil;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;

import java.time.Duration;

/**
 * Description:
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/8/21 18:18
 */
public class CustomCacheManager extends RedisCacheManager {

    private static final String SPLIT_CHAT = "#";

    public CustomCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
    }

    /**
     * 重写父类createRedisCache方法解析表达式@Cacheable(value = "e_cer#60")去#号后面数字设置为过期时间默认单位分钟
     *
     * @param name 名称
     */
    @Override
    protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {
        //名称中存在#标记进行到期时间配置
        if (name.contains(SPLIT_CHAT)) {
            String[] split = name.split(SPLIT_CHAT);
            if (StrUtil.isNumeric(split[1])) {
                //配置缓存到期时间
                int cycle = Integer.parseInt(split[1]);
                return super.createRedisCache(split[0], cacheConfig.entryTtl(Duration.ofMinutes(cycle)));
            }
        }
        return super.createRedisCache(name, cacheConfig);
    }

}
