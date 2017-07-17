package com.org.spring.boot.amqp;

import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ErrorHandler;

@Configuration
public abstract class MessagingConsumerConfiguration {

    @Autowired
    protected CachingConnectionFactory connectionFactory;

    protected abstract MessageListener messageListener();

    protected abstract String queue();

    protected SimpleMessageListenerContainer setupSimpleMessageListenerContainer() {
        final SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(this.connectionFactory);
        container.setQueueNames(queue());
        container.setConcurrentConsumers(4);
        container.setMaxConcurrentConsumers(10);
        container.setMessageListener(messageListener());
        container.setMessageConverter(jsonMessageConverted());
        container.setErrorHandler(errorHandler());
        return container;
    }

    @Bean
    protected JsonMessageConverter jsonMessageConverted() {
        return new JsonMessageConverter();
    }

    @Bean
    protected ErrorHandler errorHandler() {
//        return new ConditionalRejectingErrorHandler(new MessageListenerExceptionHandler());
        return new ConditionalRejectingErrorHandler();
    }

}
