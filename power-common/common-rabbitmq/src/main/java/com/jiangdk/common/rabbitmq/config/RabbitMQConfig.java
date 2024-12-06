package com.jiangdk.common.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: JiangDk
 * @date: 2024/12/6 17:36
 * @description:
 */
@Configuration
public class RabbitMQConfig {
    /**
     * 消息转换器
     * @return
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // 声明交换机
    @Bean
    DirectExchange defaultDirectExchange() {
        return new DirectExchange("pms.goods");
    }

    // 创建三个队列
    @Bean
    public Queue defaultDirectQueue1() {
        return new Queue("pms.goods.save");
    }

    @Bean
    public Queue defaultDirectQueue2() {
        return new Queue("pms.goods.update");
    }

    @Bean
    public Queue defaultDirectQueue3() {
        return new Queue("pms.goods.del");
    }

    // 交换机与队列绑定 - 每个队列使用不同的路由键
    @Bean
    public Binding defaultTopicModeBinding1(Queue defaultDirectQueue1, DirectExchange defaultDirectExchange) {
        return BindingBuilder.bind(defaultDirectQueue1).to(defaultDirectExchange).with("pms.goods.save");
    }

    @Bean
    public Binding defaultTopicModeBinding2(Queue defaultDirectQueue2, DirectExchange defaultDirectExchange) {
        return BindingBuilder.bind(defaultDirectQueue2).to(defaultDirectExchange).with("pms.goods.update");
    }

    @Bean
    public Binding defaultTopicModeBinding3(Queue defaultDirectQueue3, DirectExchange defaultDirectExchange) {
        return BindingBuilder.bind(defaultDirectQueue3).to(defaultDirectExchange).with("pms.goods.del");
    }
}
