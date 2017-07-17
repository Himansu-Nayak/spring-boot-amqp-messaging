package com.org.spring.boot.amqp.processor;

import com.org.spring.boot.amqp.message.Message;
import com.org.spring.boot.amqp.publisher.InspectionMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class Processor {

    @Autowired
    protected InspectionMessagePublisher inspectionMessagePublisher;

    protected abstract void processMessage(final Message message);

}
