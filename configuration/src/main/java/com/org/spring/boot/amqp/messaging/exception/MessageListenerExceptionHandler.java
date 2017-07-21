package com.org.spring.boot.amqp.messaging.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException;
import org.springframework.util.ErrorHandler;


@Slf4j
public class MessageListenerExceptionHandler implements ErrorHandler {

    private String exchange;
    private String queue;

    public MessageListenerExceptionHandler(final String exchange, final String queue) {
        this.exchange = exchange;
        this.queue = queue;
    }

    @Override
    public void handleError(final Throwable throwable) {
        if (throwable instanceof ListenerExecutionFailedException) {
            final ListenerExecutionFailedException listenerExecutionFailedException = (ListenerExecutionFailedException) throwable;
            log.error("Error during listening exchange [{}] queue[{}] for the message [{}]", exchange, queue, listenerExecutionFailedException
                    .getFailedMessage());
        }
    }
}
