package com.ghoject.cloud.helloworld.config;

import com.ghoject.cloud.helloworld.bean.RedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;


@Configuration
public class RedisConfiguration {

    @Autowired
    private RedisConfig redisConfig;

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        System.out.println(redisConfig);
        RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration(redisConfig.getHost(),redisConfig.getPort());

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(redisConfig.getJedis().getPool().getMaxIdle());
        jedisPoolConfig.setMinIdle(redisConfig.getJedis().getPool().getMinIdle());
        jedisPoolConfig.setMaxTotal(redisConfig.getJedis().getPool().getMaxActive());
        jedisPoolConfig.setMaxWaitMillis(redisConfig.getTimeout());

        JedisClientConfiguration clientConfig = JedisClientConfiguration.builder().connectTimeout(Duration.ofMillis(redisConfig.getTimeout()))
                .readTimeout(Duration.ofMillis(redisConfig.getTimeout())).usePooling().poolConfig(jedisPoolConfig).build();

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(standaloneConfig,clientConfig);
        return jedisConnectionFactory;
    }

    /**
     * 使用String（key）和Json（value）序列化，代替默认的JdkSerializationRedisSerializer（ByteArray）
     * @return
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate() {
        RedisTemplate<Object, Object> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory());

        template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);

        return template;
    }

}
