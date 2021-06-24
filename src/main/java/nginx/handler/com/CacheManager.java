package nginx.handler.com;


import nginx.handler.com.redis.RedisConfig;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

public class CacheManager {

    private final RMap<String, String> cache;
    private static CacheManager cacheManager;

    public CacheManager(RedissonClient redissonClient) {
        this.cache = redissonClient.getMap("cache_javanix");
    }

    public RMap<String, String> getCache() {
        return cache;
    }

    public static synchronized CacheManager get() {
        if (cacheManager == null) {
            cacheManager = new CacheManager(RedisConfig.getRedisClient());
        }
        return cacheManager;
    }

}
