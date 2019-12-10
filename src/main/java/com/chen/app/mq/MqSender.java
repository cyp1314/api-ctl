package com.chen.app.mq;

import com.chen.app.data.ApiCusGoodData;
import com.chen.app.service.ApiCusGoodService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class MqSender implements RabbitTemplate.ReturnCallback, RabbitTemplate.ConfirmCallback {
    @Autowired
    ApiCusGoodService cusGoodService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String key) {
        String good = (String) redisTemplate.opsForValue().get(key);
        ObjectMapper mapper = new ObjectMapper();
        ApiCusGoodData cusGoodData = null;
        try {
            cusGoodData = mapper.readValue(good, ApiCusGoodData.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (cusGoodData.getGoodType() == 0 || cusGoodData.getGoodType() == 1) {
            this.rabbitTemplate.convertAndSend("api-request-over",key);
        }
    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        System.out.println("sender return success" + message.toString()+"==="+i+"==="+s1+"==="+s2);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (!ack) {
            // 定时任务下次再发送
            System.out.println("HelloSender消息发送失败" + cause + correlationData.toString());
        } else {
            System.out.println("HelloSender 消息发送成功 ");
        }
    }
}
