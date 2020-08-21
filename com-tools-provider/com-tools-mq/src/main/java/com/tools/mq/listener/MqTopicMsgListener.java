package com.tools.mq.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @auther Macro
 * @date 2019/12/16 16:03
 * @Description rabbitmq 主题消息监听
 */
@Component
public class MqTopicMsgListener {


    @RabbitListener(queues = "szl_token_topic_msg_queue")
    public void receiveDirect1(Message message, Channel channel) throws Exception{
        System.out.println("【receiveDirect1监听到消息】" + new String(message.getBody()));
        // 消息手动确认
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }

}
