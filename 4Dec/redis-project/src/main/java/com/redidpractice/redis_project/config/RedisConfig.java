package com.redidpractice.redis_project.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

@Configuration
public class RedisConfig {

    @Value("${redis.host}")
    private String redisHost;

    @Value("${redis.port}")
    private int redisPort;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration configuration =
                new RedisStandaloneConfiguration(redisHost, redisPort);

        return new LettuceConnectionFactory(configuration);
    }

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {

        // default config – 10 minutes TTL
        RedisCacheConfiguration defaultCacheConfig =
                myDefaultCacheConfig(Duration.ofMinutes(10))
                        .disableCachingNullValues();

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(defaultCacheConfig)
                // for @Cacheable("tutorials")
                .withCacheConfiguration("tutorials",
                        myDefaultCacheConfig(Duration.ofMinutes(5)))
                // for @Cacheable("tutorial")
                .withCacheConfiguration("tutorial",
                        myDefaultCacheConfig(Duration.ofMinutes(1)))
                // for @Cacheable("published_tutorials")
                .withCacheConfiguration("published_tutorials",
                        myDefaultCacheConfig(Duration.ofMinutes(2)))
                .build();
    }

    private RedisCacheConfiguration myDefaultCacheConfig(Duration ttl) {
        return RedisCacheConfiguration
                .defaultCacheConfig()
                .entryTtl(ttl)
                .serializeValuesWith(
                        SerializationPair.fromSerializer(
                                new GenericJackson2JsonRedisSerializer()
                        )
                );
    }
}
