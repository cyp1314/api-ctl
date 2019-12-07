package com.chen.app.mq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqSender implements RabbitTemplate.ReturnCallback, RabbitTemplate.ConfirmCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String appkey){
        this.rabbitTemplate.convertAndSend("api-request-over",appkey);
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
