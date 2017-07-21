package com.org.spring.boot.amqp.error;

public class MessageUnMarshallingException extends RuntimeException {

    public MessageUnMarshallingException(final String message, final Exception exception) {
        super(message, exception);
    }
}
