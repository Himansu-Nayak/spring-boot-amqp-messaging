/*
package com.org.amqp.messaging.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException;


@Slf4j
public class MessageListenerExceptionHandler extends ConditionalRejectingErrorHandler.DefaultExceptionStrategy {

    @Override
    public boolean isFatal(final Throwable throwable) {
        if (throwable instanceof ListenerExecutionFailedException) {
            ListenerExecutionFailedException listenerExecutionFailedException = (ListenerExecutionFailedException) throwable;
            log.error("Failed to process inbound message from queue "
                              + listenerExecutionFailedException.getFailedMessage()
                                                                .getMessageProperties()
                                                                .getConsumerQueue()
                              + "; failed message: " + listenerExecutionFailedException.getFailedMessage(), throwable);
        }
        return super.isFatal(throwable);
    }

}
*/
