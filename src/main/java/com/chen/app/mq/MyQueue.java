package com.chen.app.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyQueue {

    @Bean
    public Queue overQueue(){
        return new Queue("api-request-over");
    }
    @Bean
    public Queue logQueue(){
        return new Queue("api-request-log");
    }
}
