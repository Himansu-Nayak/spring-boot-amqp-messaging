package com.org.spring.boot.amqp.error;

public class MessageMarshallingException extends RuntimeException {

    public MessageMarshallingException(final String message, final Exception exception) {
        super(message, exception);
    }
}
