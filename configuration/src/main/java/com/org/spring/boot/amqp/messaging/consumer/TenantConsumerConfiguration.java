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
public class TenantConsumerConfiguration extends MessagingConsumerConfiguration {

    @Value("${tenant.messaging.exchange}")
    private String tenantExchange;

    @Value("${tenant.messaging.queue}")
    private String tenantQueue;

    @Value("${tenant.messaging.routing-key}")
    private String tenantRoutingKey;

    @Bean
    public Queue tenantQueue() {
        return QueueBuilder.durable(queue()).build();
    }

    @Bean
    public TopicExchange tenantExchange() {
        return new TopicExchange(tenantExchange);
    }

    @Bean
    public Binding bindToTenantExchange() {
        return bind(tenantQueue()).to(tenantExchange()).with(tenantRoutingKey);
    }

    @Bean
    public SimpleMessageListenerContainer setupTenantListener() {
        return setupSimpleMessageListenerContainer();
    }

    @Override
    public String queue() {
        return tenantQueue;
    }

    @Override
    public String exchange() {
        return tenantExchange;
    }

}