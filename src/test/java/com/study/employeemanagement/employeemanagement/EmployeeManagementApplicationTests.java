package com.study.employeemanagement.employeemanagement;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmployeeManagementApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Test
    void contextLoads() {
    }

    @Test
    void sendRabbitMQ(){
        Map<String, Object> object = new HashMap<String, Object>();
        object.put("key1",123);
        object.put("key2",true);
        object.put("key3", Arrays.asList("abc","def"));
        rabbitTemplate.convertAndSend("emp.direct","zhangsan.aig", object);
    }

    @Test
    public void receiveRabbitMQ(){
        Object o = rabbitTemplate.receiveAndConvert("zhangsan.aig");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    @Test
    public void register(){
        DirectExchange directExchange = new DirectExchange("amqpAdmin.direct");
        Queue queue = new Queue("amqpAdmin.queue");
        amqpAdmin.declareExchange(directExchange);
        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareBinding(
            new Binding("amqpAdmin.queue", DestinationType.QUEUE, "amqpAdmin.direct", "amqpAdmin.queue", null));
    }

}
