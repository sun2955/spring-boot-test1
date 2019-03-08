package com.sun.demo.rabbitmq.config;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue Queue() {
        return new Queue("hello");
    }

	/**
	 * 日志队列
	 * @return
	 */
    @Bean
    public Queue queueLogs() {
        return new Queue("logs",true);
    }

    /**
     * 推送动态队列
     * @return
     */
    @Bean
    public Queue queueProjectNews() {
    	return new Queue("projectNews",true);
    }
   
    /**
     * 根据匹配条件匹配分发的路由
     * @return
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange("topicExchange");
    }
    /**
     * 广播路由（当队列需要收到广播消息的是否绑定改路由即可）
     * @return
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }
/*   *//**
     * 给队列绑定根据匹配条件匹配分发的路由
     * @param queueLogs
     * @param exchange
     * @return
     *//*
    @Bean
    Binding bindingTopicExchange(Queue queueLogs, TopicExchange exchange) {
    	*//**
    	 * 将队列绑定到exchange交换机。交换机接收到的以logs.开头的消息都分发至队列中。
    	 *//*
        return BindingBuilder.bind(queueLogs).to(exchange).with("logs.#");
    }
    *//**
     * 给队列绑定广播路由
     * @param queueLogs
     * @param exchange
     * @return
     *//*
    @Bean
    Binding bindingFanoutExchange(Queue queueLogs, FanoutExchange exchange) {
        return BindingBuilder.bind(queueLogs).to(exchange);
    }*/

    /**
     * 操作日志队列
     */
    @Bean
    public Queue queueMonitorPointLog() {
        return new Queue("monitorPointLog",true);
    }

}
