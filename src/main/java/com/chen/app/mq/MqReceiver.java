package com.chen.app.mq;

import com.chen.app.data.ApiCusGoodData;
import com.chen.app.service.ApiCusGoodService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "api-request-over")
public class MqReceiver {

    @Autowired
    ApiCusGoodService cusGoodService;

    @Autowired
    RedisTemplate redisTemplate;

    @RabbitHandler
    public void process(String key, Channel channel, Message message) throws Exception {

        String good = (String) redisTemplate.opsForValue().get(key);
        ObjectMapper mapper = new ObjectMapper();
        ApiCusGoodData cusGoodData = mapper.readValue(good, ApiCusGoodData.class);
        if (cusGoodData.getGoodType() == 0 || cusGoodData.getGoodType() == 1) {
            synchronized (this) {
                if (cusGoodData.getApiNumbers() > 0) {
                    cusGoodData.setApiNumbers(cusGoodData.getApiNumbers() - 1);
                    redisTemplate.opsForValue().set(key,mapper.writeValueAsString(cusGoodData));

                    cusGoodService.updateApiNumers(key,cusGoodData.getApiNumbers());
                    //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
                    channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                    System.out.println(key + " receiver success");
                } else {
                    //丢弃这条消息
                    channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
                    System.out.println(key + " receiver fail");
                }
            }
        }
    }
}
