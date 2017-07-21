package com.org.spring.boot.amqp.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class InspectionMessagePublisher {

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected CachingConnectionFactory connectionFactory;

    @Value("${inspection.messaging.exchange}")
    private String inspectionExchange;

    public void sendMessage(final List<String> inspectionMessageEnvelopes) {
        try {
            for (final String inspectionMessageEnvelope : inspectionMessageEnvelopes) {
                final String jsonMessage = objectMapper.writeValueAsString(inspectionMessageEnvelope);
                final Message message = new Message(jsonMessage.getBytes(), new MessageProperties());
                final String routingKey = "jci.tofs.inspection." + new Random().nextInt(1000);
                rabbitTemplate().convertAndSend(inspectionExchange, routingKey, message);
                log.info("Message [ {} ] is published with routing key [ {} ]", jsonMessage, routingKey);
            }
        } catch (final JsonProcessingException jsonParseException) {
            log.error("Parsing exception ", jsonParseException);
        }
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        RetryTemplate retryTemplate = new RetryTemplate();
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        //backOffPolicy.setInitialInterval(200);
        //backOffPolicy.setMultiplier(2.0);
        //backOffPolicy.setMaxInterval(100000);
        retryTemplate.setBackOffPolicy(backOffPolicy);

        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(3);
        retryTemplate.setRetryPolicy(retryPolicy);

        rabbitTemplate.setRetryTemplate(retryTemplate);
        //rabbitTemplate.setRecoveryCallback(recoveryCallBack);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());

        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
