package com.org.spring.boot.amqp.publisher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InspectionMessagePublisher {

    @Value("${messaging.exchange.inspection}")
    private String inspectionExchange;

    @Value("${messaging.routing-key.inspection}")
    private String inspectionRoutingKey;

    public void sendMessage(String jsonMessage) {
        Message message = new Message(jsonMessage.getBytes(), new MessageProperties());
        log.info("Message to be send : {}", jsonMessage);
        //rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        //rabbitTemplate.convertAndSend(inspectionExchange, inspectionRoutingKey, message);
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
