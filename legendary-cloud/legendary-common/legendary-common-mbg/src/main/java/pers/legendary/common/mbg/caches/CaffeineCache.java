package pers.legendary.common.mbg.caches;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.Cache;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Description: CaffeineCache
 *  因为二级缓存的弊大于利，暂不考虑用二级缓存
 * @author YangYang
 * @version 1.0.0
 * @date 2022/4/25 10:33
 */
@Slf4j
public final class CaffeineCache implements Cache {

    /**
     * 读写锁
     */
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    private final com.github.benmanes.caffeine.cache.Cache<Object, Object> cache;

    private final String id;

    /**
     * Instantiates a new caffeine cache.
     *
     * @param id the id
     */
    public CaffeineCache(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }

        this.cache = Caffeine.newBuilder().build();
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        this.cache.put(key, value);
    }

    @Override
    public Object getObject(Object key) {
        return this.cache.getIfPresent(key);
    }

    @Override
    public Object removeObject(Object key) {
        return this.cache.asMap().remove(key);
    }

    @Override
    public void clear() {
        this.cache.invalidateAll();
    }

    @Override
    public int getSize() {
        return (int) this.cache.estimatedSize();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }
}
