package com.org.spring.boot.amqp.messaging.consumer;

import com.org.spring.boot.amqp.messaging.MessagingConsumerConfiguration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.amqp.core.BindingBuilder.bind;

@Configuration
public class BridgeConsumerConfiguration extends MessagingConsumerConfiguration {

    @Value("${bridge.messaging.exchange}")
    private String bridgeExchange;

    @Value("${bridge.messaging.queue}")
    private String bridgeQueue;

    @Value("${bridge.messaging.routing-key}")
    private String bridgeRoutingKey;

    @Bean
    public Queue bridgeQueue() {
        return QueueBuilder.durable(queue()).build();
    }

    @Bean
    public TopicExchange bridgeExchange() {
        return new TopicExchange(bridgeExchange);
    }

    @Bean
    public Binding bindToBridgeExchange() {
        return bind(bridgeQueue()).to(bridgeExchange()).with(bridgeRoutingKey);
    }

    @Bean
    public SimpleMessageListenerContainer setupBridgeListener() {
        return setupSimpleMessageListenerContainer();
    }

    @Override
    public String queue() {
        return bridgeQueue;
    }

    @Override
    public String exchange() {
        return bridgeExchange;
    }

}
