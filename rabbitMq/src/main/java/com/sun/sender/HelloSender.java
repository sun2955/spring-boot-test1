package com.sun.sender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
  * @title: 消息生产者
  * @projectName com.sun.yinghy.rabbitmq.sender
  * @description: TODO
  * @author :szy
  * @date 2020/7/29 14:08
 */
@Component
@Slf4j
public class HelloSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //测试简单队列
    public void send(String name) {
        name = "生产者1====="+name;
        String context = name + "-hello-" + new Date();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }

    //广播推送
    public void guangbosend(String name) {
        name = "生产者1====="+name;
        String context = name + "-hello-" + new Date();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }


    /**
     * 测试广播模式.
     *
     * @param p the p
     * @return the response entity
     */
    public void broadcast(String p) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        //直接向交换机推推送消息
        rabbitTemplate.convertAndSend("FANOUT_EXCHANGE", "", p, correlationData);
    }


    /**
     * 测试Direct模式.
     *
     * @param p the p
     * @return the response entity
     */
    public void direct(String p) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("DIRECT_EXCHANGE", "DIRECT_ROUTING_KEY", p, correlationData);
    }


    public void send(int i) {
        String name = "生产者2=====";
        String context = name + "-hello-" + new Date() + "["+ i + "]";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }
}
