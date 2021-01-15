package com.study.employeemanagement.employeemanagement.config;

import java.net.UnknownHostException;

import com.study.employeemanagement.employeemanagement.service.dto.EmployeeDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * Description:
 *
 * @author runtian.zsl
 * @date 2021/1/12 9:44 上午
 */

@Configuration
public class MyRedisConfig {

    @Bean
    public RedisTemplate<String, EmployeeDTO> myRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<String, EmployeeDTO> template = new RedisTemplate();
        Jackson2JsonRedisSerializer<EmployeeDTO> redisSerializer = new Jackson2JsonRedisSerializer<EmployeeDTO>(EmployeeDTO.class);
        template.setDefaultSerializer(redisSerializer);
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}
