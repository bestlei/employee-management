package com.study.employeemanagement.employeemanagement;

import com.alibaba.fastjson.JSON;

import com.study.employeemanagement.employeemanagement.service.dto.EmployeeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class EmployeeManagementApplicationTests {

    @Autowired
    StringRedisTemplate stringRedisTemplate;   // 操作字符串

    @Autowired
    RedisTemplate redisTemplate; // 操作Object

    @Autowired
    RedisTemplate<String, EmployeeDTO> myRedisTemplate;  // 操作k-v都是对象的


    @Test
    void contextLoads() {
    }

    /**
     * String、List、Set、Hash、Zset
     * stringRedisTemplate.opsForValue()
     * stringRedisTemplate.opsForList()
     * stringRedisTemplate.opsForSet()
     * stringRedisTemplate.opsForHash()
     * stringRedisTemplate.opsForZSet()
     */
    @Test
    public void testRedis(){
        // 操作key为字符串
        // stringRedisTemplate.opsForValue().append("msg","hello");
        // stringRedisTemplate.opsForList().leftPush("myList","1");
        // stringRedisTemplate.opsForHash().put("myHash","key1","value1");
        // stringRedisTemplate.opsForHash().put("myHash","key2","value2");

        // 操作key为Object
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeId(123L);
        // 第一种方式，存储前直接序列化
        stringRedisTemplate.opsForValue().set("employee1",JSON.toJSONString(employeeDTO));
        // 第二种方式，自定义redisTemplate序列化对象
        myRedisTemplate.opsForValue().set("employee2", employeeDTO);
    }

}
