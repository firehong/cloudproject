package com.tools.mq.service;


import com.tools.mq.config.RabbitmpConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther Macro
 * @date 2019/12/16 15:55
 * @Description rabbitmq 主题模式接口
 */
@Service
@Slf4j
public class MqTopicProducerService{

    @Autowired
    protected AmqpTemplate amqpTemplate; // rabbitmq 消息队列

    /**
     * @date 2019/12/16 16:00
     * @Description 发送队列消息
     * @Param key 主题消息 路由key
     * @Param msg json消息
     */
    public boolean sendMqMsg(String key, String msg){
        try {
            amqpTemplate.convertAndSend(RabbitmpConfig.TOPIC_EXCHANGE, key, msg);
            return true;
        }catch (Exception e){
            log.error("[API] rabbitmq 消息发送失败, 异常信息，{}", e.getMessage());
            return false;
        }
    }



}
