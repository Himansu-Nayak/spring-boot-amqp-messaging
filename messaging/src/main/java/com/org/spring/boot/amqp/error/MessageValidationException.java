package com.org.spring.boot.amqp.error;

import java.util.List;

/**
 * Reports about invalid message
 */
public class MessageValidationException extends RuntimeException {

    private final List<String> validationErrors;

    public MessageValidationException(String message, List<String> validationErrors) {
        super(message);
        this.validationErrors = validationErrors;
    }

    public List<String> getValidationErrors() {
        return validationErrors;
    }
}
