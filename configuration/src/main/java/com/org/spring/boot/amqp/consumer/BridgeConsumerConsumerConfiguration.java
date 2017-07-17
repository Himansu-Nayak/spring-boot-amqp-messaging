package com.org.spring.boot.amqp.consumer;

import com.org.spring.boot.amqp.MessagingConsumerConfiguration;
import com.org.spring.boot.amqp.processor.ProcessorAdapter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.amqp.core.BindingBuilder.bind;

@Configuration
public class BridgeConsumerConsumerConfiguration extends MessagingConsumerConfiguration {

    @Value("${messaging.exchange.bridge}")
    private String bridgeExchange;

    @Value("${messaging.queue.bridge}")
    private String bridgeQueue;

    @Value("${messaging.routing-key.bridge}")
    private String bridgeRoutingKey;

    @Bean
    Queue bridgeQueue() {
        return new Queue(queue());
    }

    @Bean
    TopicExchange bridgeExchange() {
        return new TopicExchange(bridgeExchange);
    }

    @Bean
    Binding bindToBridgeExchange() {
        return bind(bridgeQueue()).to(bridgeExchange()).with(bridgeRoutingKey);
    }

    @Bean
    SimpleMessageListenerContainer setupBridgeListener() {
        return setupSimpleMessageListenerContainer();
    }

    @Bean
    ProcessorAdapter processorAdapter() {
        return new ProcessorAdapter();
    }

    public String queue() {
        return bridgeQueue;
    }

    public MessageListener messageListener() {
        return message -> processorAdapter().process(message);
    }

}
