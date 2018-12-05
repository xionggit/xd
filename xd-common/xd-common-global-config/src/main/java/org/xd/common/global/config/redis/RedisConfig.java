package org.xd.common.global.config.redis;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableCaching
@EnableTransactionManagement
public class RedisConfig extends CachingConfigurerSupport {
    
    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        
        template.setKeySerializer(stringRedisSerializer());
        template.setHashKeySerializer(stringRedisSerializer());
        template.setValueSerializer(defaultRedisSerializer());
        template.setHashValueSerializer(defaultRedisSerializer());
        
        template.setConnectionFactory(lettuceConnectionFactory);
        template.afterPropertiesSet();
        return template;
    }
    
    @Bean
    public RedisTemplate<Object, Object> redisObjectTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setValueSerializer(defaultRedisSerializer());
        template.setConnectionFactory(lettuceConnectionFactory);
        template.afterPropertiesSet();
        return template;
    }
    
    public StringRedisSerializer stringRedisSerializer() {
        return new StringRedisSerializer();
    }

    //使用GenericJackson2JsonRedisSerializer序列化对象需要无参构造方法无法。
    //TODO 在Spring Security待测
    @Bean("springSessionDefaultRedisSerializer")
    public RedisSerializer<Object> defaultRedisSerializer(){
//        log.info("自定义Redis Session序列化加载成功");
//        return new FastJsonRedisSerializer<>(Object.class);
        return new GenericJackson2JsonRedisSerializer();
    }
    
	/**
     * 缓存配置管理器
     */
    @Bean
    public CacheManager cacheManager(LettuceConnectionFactory factory) {
        RedisCacheWriter writer = RedisCacheWriter.lockingRedisCacheWriter(factory);
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        RedisCacheManager cacheManager = new RedisCacheManager(writer, config);
        return cacheManager;
    }

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(){
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
//        configuration = configuration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(defaultRedisSerializer()));
        return configuration;
    }
    
}
