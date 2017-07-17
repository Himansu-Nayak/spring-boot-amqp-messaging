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
public class InspectionConsumerConsumerConfiguration extends MessagingConsumerConfiguration {

    @Value("${messaging.exchange.inspection}")
    private String inspectionExchange;

    @Value("${messaging.queue.inspection}")
    private String inspectionQueue;

    @Value("${messaging.routing-key.inspection}")
    private String inspectionRoutingKey;

    @Bean
    Queue inspectionQueue() {
        return new Queue(inspectionQueue);
    }

    @Bean
    TopicExchange inspectionExchange() {
        return new TopicExchange(inspectionExchange);
    }

    @Bean
    Binding bindToInspectionExchange() {
        return bind(inspectionQueue()).to(inspectionExchange()).with(inspectionRoutingKey);
    }

    @Bean
    SimpleMessageListenerContainer setupInspectionListener() {
        return setupSimpleMessageListenerContainer();
    }

    public String queue() {
        return inspectionQueue;
    }

    @Bean
    ProcessorAdapter processorAdapter() {
        return new ProcessorAdapter();
    }

    @Override
    public MessageListener messageListener() {
        return message -> processorAdapter().process(message);
    }

}
