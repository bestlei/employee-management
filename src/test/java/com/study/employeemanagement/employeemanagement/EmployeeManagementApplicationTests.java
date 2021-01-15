package com.study.employeemanagement.employeemanagement;

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
    RedisTemplate<Object, Object> myRedisTemplate;  // 操作k-v都是对象的


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
        stringRedisTemplate.opsForValue().append("msg","hello");
        stringRedisTemplate.opsForList().leftPush("myList","1");
        String result = stringRedisTemplate.opsForValue().get("msg");
        System.out.println(result);
    }

    @Test
    public void testRedisSaveObject(){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeId(123L);
        // 1、自己将对象转化为json
        // redisTemplate.opsForValue().set("employee", JSON.toJSONString(employeeDO));
        // 2、定制redis序列化配置
        myRedisTemplate.opsForValue().set("employee",employeeDTO);
        EmployeeDTO employeeDTORedisResult = (EmployeeDTO)myRedisTemplate.opsForValue().get("employee");
        System.out.println(employeeDTO);
    }

}
