package TableTap.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Value("${spring.profiles.active}")
    private String profile;

    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private int port;

    @Bean
    public RedissonClient redissonClient() {
        if (profile.equals("dev")) {
            Config config = new Config();
            config.useSingleServer()
                    .setAddress("redis://" + host + ":" + port)
                    .setConnectionPoolSize(64)
                    .setConnectionMinimumIdleSize(24)
                    .setConnectTimeout(10000)
                    .setRetryAttempts(3);
            return Redisson.create(config);
        }
        return null;
    }
}
