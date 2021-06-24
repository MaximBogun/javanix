package nginx.handler.com.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedisConfig {

    private static RedissonClient redissonClient = null;

    public static RedissonClient getRedisClient() {
        if (redissonClient == null) {
            Config config = new Config();
            config.useSingleServer()
                    .setAddress(System.getenv("REDIS_HOST"))
                    .setPassword(System.getenv("REDIS_PASSWORD"));
            redissonClient = Redisson.create(config);
        }
        return redissonClient;
    }

}
