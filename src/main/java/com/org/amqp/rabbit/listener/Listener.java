package com.org.amqp.rabbit.listener;

import com.org.amqp.rabbit.configuration.RabbitMQConfiguration;
import com.org.amqp.rabbit.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class Listener {

    private static final Logger log = LoggerFactory.getLogger(Listener.class);

    @RabbitListener(queues = RabbitMQConfiguration.QUEUE_GENERIC_NAME)
    public void receiveMessage(final org.springframework.amqp.core.Message message) {
        log.info("Received message as generic: {}", message.toString());
    }

    @RabbitListener(queues = RabbitMQConfiguration.QUEUE_SPECIFIC_NAME)
    public void receiveMessage(final Message message) {
        log.info("Received message as specific class: {}", message.toString());
    }
}
