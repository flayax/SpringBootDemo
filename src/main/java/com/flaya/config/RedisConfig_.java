package com.flaya.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.singletonMap;

//@Configuration
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600 * 12)//最大过期时间
//@EnableCaching
public class RedisConfig_ {

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        return new RedisCacheManager(
                RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
                this.getRedisCacheConfigurationWithTtl(30*60), // 默认策略，未配置的 key 会使用这个
                this.getRedisCacheConfigurationMap() // 指定 key 策略
        );
    }

    private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
        //SsoCache和BasicDataCache进行过期时间配置
        redisCacheConfigurationMap.put("SsoCache", this.getRedisCacheConfigurationWithTtl(24*60*60));
        redisCacheConfigurationMap.put("BasicDataCache", this.getRedisCacheConfigurationWithTtl(30*60));
        return redisCacheConfigurationMap;
    }

    private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Integer seconds) {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(
                RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(jackson2JsonRedisSerializer)
        ).entryTtl(Duration.ofSeconds(seconds));

        return redisCacheConfiguration;
    }

//    @Bean
//    public KeyGenerator wiselyKeyGenerator() {
//        return new KeyGenerator() {
//            @Override
//            public Object generate(Object target, Method method, Object... params) {
//                StringBuilder sb = new StringBuilder();
//                sb.append(target.getClass().getName());
//                sb.append("." + method.getName());
//                if(params==null||params.length==0||params[0]==null){
//                    return null;
//                }
//                String join = String.join("&", Arrays.stream(params).map(Object::toString).collect(Collectors.toList()));
//                String format = String.format("%s{%s}", sb.toString(), join);
//                //log.info("缓存key：" + format);
//                return format;
//            }
//        };
//    }


//    @Bean
//    CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
//
//        /* 默认配置， 默认超时时间为30s */
//        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration
//                .ofSeconds(30L)).disableCachingNullValues();
//
//        /* 配置test的超时时间为120s*/
//        RedisCacheManager cacheManager = RedisCacheManager.builder(RedisCacheWriter.lockingRedisCacheWriter
//                (connectionFactory)).cacheDefaults(defaultCacheConfig).withInitialCacheConfigurations(singletonMap
//                ("test", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(120L))
//                        .disableCachingNullValues())).transactionAware().build();
//
//        return cacheManager;
//    }

//    @Bean
//    public RedisTemplate<String, Integer> redisTemplate(RedisConnectionFactory factory) {
//
//        RedisTemplate<String, Integer> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
//        redisTemplate.setExposeConnection(true);
//        redisTemplate.setConnectionFactory(factory);
//        redisTemplate.afterPropertiesSet();
//
//
//        /*StringRedisTemplate template = new StringRedisTemplate(factory);
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        template.setValueSerializer(jackson2JsonRedisSerializer);
//        template.afterPropertiesSet();*/
//        return redisTemplate;
//    }

}