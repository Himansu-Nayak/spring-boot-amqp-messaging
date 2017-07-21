package com.org.spring.boot.amqp.processor.bridge;

import com.org.spring.boot.amqp.message.trouble.TroubleMessageData;
import com.org.spring.boot.amqp.processor.Event;
import com.org.spring.boot.amqp.processor.Processor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TroubleStatusProcessor extends Processor<TroubleMessageData> {

    public TroubleStatusProcessor() {
        super(TroubleMessageData.class);
    }

    @Override
    public void process(final String message) {
        super.process(message);
    }

    @Override
    protected Event buildEvent(final TroubleMessageData troubleMessageData) {
        return new Event("device.trouble.status", troubleMessageData.getSerial());
    }

}