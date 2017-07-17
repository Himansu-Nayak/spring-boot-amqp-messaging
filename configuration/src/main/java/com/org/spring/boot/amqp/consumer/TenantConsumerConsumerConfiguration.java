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
public class TenantConsumerConsumerConfiguration extends MessagingConsumerConfiguration {

    @Value("${messaging.exchange.tenant}")
    private String tenantExchange;

    @Value("${messaging.queue.tenant}")
    private String tenantQueue;

    @Value("${messaging.routing-key.tenant}")
    private String tenantRoutingKey;

    @Bean
    Queue tenantQueue() {
        return new Queue(tenantQueue);
    }

    @Bean
    TopicExchange tenantExchange() {
        return new TopicExchange(tenantExchange);
    }

    @Bean
    Binding bindToTenantExchange() {
        return bind(tenantQueue()).to(tenantExchange()).with(tenantRoutingKey);
    }

    @Bean
    SimpleMessageListenerContainer setupTenantListener() {
        return setupSimpleMessageListenerContainer();
    }

    @Bean
    ProcessorAdapter processorAdapter() {
        return new ProcessorAdapter();
    }

    public String queue() {
        return tenantQueue;
    }

    @Override
    public MessageListener messageListener() {
        return message -> processorAdapter().process(message);
    }

}
