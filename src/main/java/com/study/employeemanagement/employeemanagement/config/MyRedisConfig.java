package com.study.employeemanagement.employeemanagement.config;

import com.study.employeemanagement.employeemanagement.dao.entity.DepartmentDO;
import com.study.employeemanagement.employeemanagement.service.dto.EmployeeDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

/**
 * Description:
 *
 * @author best.lei
 * @date 2021/1/12 9:44 上午
 */

@Configuration
public class MyRedisConfig {

    @Bean
    @Primary
    public RedisCacheManager empRedisCacheManager(RedisConnectionFactory redisConnectionFactory){
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
            .serializeValuesWith(RedisSerializationContext.SerializationPair
                .fromSerializer(new Jackson2JsonRedisSerializer<EmployeeDTO>(EmployeeDTO.class)));
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.builder(redisConnectionFactory)
            .cacheDefaults(redisCacheConfiguration);
        return builder.build();
    }

    @Bean
    public RedisCacheManager deptRedisCacheManager(RedisConnectionFactory redisConnectionFactory){
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
            .serializeValuesWith(RedisSerializationContext.SerializationPair
                .fromSerializer(new Jackson2JsonRedisSerializer<DepartmentDO>(DepartmentDO.class)));
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.builder(redisConnectionFactory)
            .cacheDefaults(redisCacheConfiguration);
        return builder.build();
    }
}
