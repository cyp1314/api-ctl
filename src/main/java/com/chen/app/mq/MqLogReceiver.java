package com.chen.app.mq;

import com.chen.app.data.ApiCusGoodData;
import com.chen.app.entity.ApiSysLogs;
import com.chen.app.service.ApiCusGoodService;
import com.chen.app.service.ApiSysLogsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "api-request-log")
public class MqLogReceiver {

    @Autowired
    ApiSysLogsService sysLogsService;

    @RabbitHandler
    public void process(String json, Channel channel, Message message) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ApiSysLogs apiSysLogs = mapper.readValue(json, ApiSysLogs.class);

        boolean save = sysLogsService.save(apiSysLogs);
        if (save){
            //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            System.out.println(apiSysLogs.getId() + " receiver success");
        }
    }
}
