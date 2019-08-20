package com.flaya.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.singletonMap;

@Slf4j
@Configuration
@EnableCaching
public class RedisConfig {

    @Autowired
    private RedisTemplate redisTemplate;

    @Bean
    public RedisTemplate redisTemplateInit() {
        //设置序列化Key的实例化对象
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置序列化Value的实例化对象
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }

//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//        return new RedisCacheManager(
//                RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
//                this.getRedisCacheConfigurationWithTtl(30*60), // 默认策略，未配置的 key 会使用这个
//                this.getRedisCacheConfigurationMap() // 指定 key 策略
//        );
//    }
//
//    private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
//        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
//        //SsoCache和BasicDataCache进行过期时间配置
//        redisCacheConfigurationMap.put("girl", this.getRedisCacheConfigurationWithTtl(24*60*60));
//        redisCacheConfigurationMap.put("test", this.getRedisCacheConfigurationWithTtl(30*60));
//        return redisCacheConfigurationMap;
//    }
//
//    private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Integer seconds) {
//        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//
//        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
//        redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(
//                RedisSerializationContext
//                        .SerializationPair
//                        .fromSerializer(jackson2JsonRedisSerializer)
//        ).entryTtl(Duration.ofSeconds(seconds));
//
//        return redisCacheConfiguration;
//    }

//    @Bean
//    CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
//
//        /* 默认配置， 默认超时时间为30m */
//        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration
//                .ofMinutes(30L)).disableCachingNullValues();
//
//        /* 配置test的超时时间为5m*/
//        RedisCacheManager cacheManager = RedisCacheManager.builder(RedisCacheWriter.lockingRedisCacheWriter
//                (connectionFactory)).cacheDefaults(defaultCacheConfig).withInitialCacheConfigurations(singletonMap
//                ("test", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(5L))
//                        .disableCachingNullValues())).transactionAware().build();
//
//        return cacheManager;
//    }

//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory factory) {
//        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();  // 生成一个默认配置，通过config对象即可对缓存进行自定义配置
//        config = config.entryTtl(Duration.ofMinutes(5))     // 设置缓存的默认过期时间，也是使用Duration设置
//                .disableCachingNullValues();     // 不缓存空值
//
//        // 设置一个初始化的缓存空间set集合
//        Set<String> cacheNames =  new HashSet<>();
//        cacheNames.add("my-redis-cache1");
//        cacheNames.add("test");
//
//        // 对每个缓存空间应用不同的配置
//        Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
//        configMap.put("my-redis-cache1", config);
//        configMap.put("test", config.entryTtl(Duration.ofSeconds(120)));
//
//        RedisCacheManager cacheManager = RedisCacheManager.builder(factory)     // 使用自定义的缓存配置初始化一个cacheManager
//                .initialCacheNames(cacheNames)  // 注意这两句的调用顺序，一定要先调用该方法设置初始化的缓存名，再初始化相关的配置
//                .withInitialCacheConfigurations(configMap)
//                .build();
//        return cacheManager;
//    }

}