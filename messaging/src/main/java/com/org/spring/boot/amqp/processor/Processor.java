package com.org.spring.boot.amqp.processor;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.spring.boot.amqp.error.MessageMarshallingException;
import com.org.spring.boot.amqp.error.MessageUnMarshallingException;
import com.org.spring.boot.amqp.error.MessageValidationException;
import com.org.spring.boot.amqp.publisher.InspectionMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.text.MessageFormat.format;
import static java.util.stream.Collectors.toList;

@Slf4j
public abstract class Processor<T> {

    private final Class<T> messageClass;

    @Autowired
    protected Validator validator;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected InspectionMessagePublisher inspectionMessagePublisher;

    protected Processor(Class<T> messageClass) {this.messageClass = messageClass;}

    protected abstract Event buildEvent(final T message);

    public void process(final String message) {
        T payload;
        try {
            payload = objectMapper.readValue(message, messageClass);
        } catch (final IOException ioException) {
            final String errorMsg = "Exception during marshalling message [ " + message + " ]";
            log.error(errorMsg, ioException);
            throw new MessageMarshallingException(errorMsg, ioException);
        }
        validate(payload);
        final Event event = buildEvent(payload);
        final List inspections = new ArrayList<>();

        publishMessage(new ArrayList<String>());
    }

    protected void publishMessage(final List inspectionMessageEnvelopes) {
        try {
            final String jsonMessage = objectMapper.writeValueAsString(inspectionMessageEnvelopes);
            inspectionMessagePublisher.sendMessage(inspectionMessageEnvelopes);
        } catch (final IOException ioException) {
            final String errorMsg = "Exception during un-marshalling message [ " + inspectionMessageEnvelopes + " ]";
            log.error(errorMsg, ioException);
            throw new MessageUnMarshallingException(errorMsg, ioException);
        }
    }

    protected void validate(final T message) {
        final Set<ConstraintViolation<T>> errors = validator.validate(message);
        if (errors != null && !errors.isEmpty()) {
            final List<String> messages = errors.stream()
                                                .map(this::createValidationMessage)
                                                .collect(toList());
            throw new MessageValidationException("Message failed validation", messages);
        }
    }

    private String createValidationMessage(final ConstraintViolation<?> error) {
        if (error.getPropertyPath() != null) {
            return format("Property ''{0}'': {1}", error.getPropertyPath(), error.getMessage());
        }
        return error.getMessage();
    }

}
