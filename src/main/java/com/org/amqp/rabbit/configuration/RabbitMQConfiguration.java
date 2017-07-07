package com.org.amqp.rabbit.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    private String exchange;
    private String bindingKey;

    public RabbitMQConfiguration(){

    }

    public RabbitMQConfiguration(@Value("${rabbitmq.exchange.name}") String exchange,
                                 @Value("${rabbitmq.exchange.binding-key}") String bindingKey) {

        this.exchange = exchange;
        this.bindingKey = bindingKey;
    }

}