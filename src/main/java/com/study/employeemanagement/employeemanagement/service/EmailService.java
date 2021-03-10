package com.study.employeemanagement.employeemanagement.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author best.lei
 * @date 2021/3/9 8:09 下午
 */
@Service
public class EmailService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendEmail(String exchange, String routingKey, String content){
        rabbitTemplate.convertAndSend(exchange, routingKey, content);
    }
}
