package com.study.employeemanagement.employeemanagement;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

// 开启基于注解的RabbitMQ的消息Listener
@EnableRabbit
@SpringBootApplication
@EnableCaching
public class EmployeeManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementApplication.class, args);
    }
}
