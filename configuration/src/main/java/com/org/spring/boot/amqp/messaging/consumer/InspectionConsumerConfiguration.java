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
public class InspectionConsumerConfiguration extends MessagingConsumerConfiguration {

    @Value("${inspection.messaging.exchange}")
    private String inspectionExchange;

    @Value("${inspection.messaging.queue}")
    private String inspectionQueue;

    @Value("${inspection.messaging.routing-key}")
    private String inspectionRoutingKey;

    @Bean
    public Queue inspectionQueue() {
        return QueueBuilder.durable(queue()).build();
    }

    @Bean
    public TopicExchange inspectionExchange() {
        return new TopicExchange(inspectionExchange);
    }

    @Bean
    public Binding bindToInspectionExchange() {
        return bind(inspectionQueue()).to(inspectionExchange()).with(inspectionRoutingKey);
    }

    @Bean
    public SimpleMessageListenerContainer setupInspectionListener() {
        return setupSimpleMessageListenerContainer();
    }

    @Override
    public String queue() {
        return inspectionQueue;
    }

    @Override
    public String exchange() {
        return inspectionExchange;
    }

}
