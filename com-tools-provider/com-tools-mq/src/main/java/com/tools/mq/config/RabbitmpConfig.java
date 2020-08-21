package com.tools.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther Macro
 * @date 2019/12/16 14:17
 * @Description  声明主题模式的队列配置
 */
@Configuration
public class RabbitmpConfig {

    /**
     * Topic 类型 队列
     */
    public static String TOPIC_MSG_QUEUE = "szl_token_topic_msg_queue";

    /**
     * Topic 类型 交换机
     */
    public static String TOPIC_EXCHANGE = "szl_token_topic_exchange";

    /**
     * Topic 类型 路由键
     */
    public static String MSG_TOPIC_ROUTING_KEY = "szl.token.#";

    /**
     * Topic 类型 短信队列
     *
     * @return
     */
    @Bean
    public Queue topicMsgQueue() {
        return new Queue(TOPIC_MSG_QUEUE);
    }

    /**
     * Topic 类型交换机
     *
     * @return
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    /**
     * 队列绑定 Topic 类型 交换机
     *
     * @return
     */
    @Bean
    public Binding msgBindingTopicExchange(Queue topicMsgQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicMsgQueue).to(topicExchange).with(MSG_TOPIC_ROUTING_KEY);
    }


}
