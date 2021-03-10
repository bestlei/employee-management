package com.study.employeemanagement.employeemanagement.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author best.lei
 * @date 2021/3/10 9:44 上午
 */
@Service
public class EmailMessageListener {

    @RabbitListener(queues = {"bestlei@Alibaba.cn","lisi@Alibaba.cn"})
    public void receive(String message){
        System.out.println(message);
    }
}
