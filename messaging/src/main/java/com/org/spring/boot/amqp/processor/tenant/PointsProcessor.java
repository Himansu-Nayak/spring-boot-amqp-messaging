package com.org.spring.boot.amqp.processor.tenant;

import com.org.spring.boot.amqp.message.PointsMessageData;
import com.org.spring.boot.amqp.processor.Event;
import com.org.spring.boot.amqp.processor.Processor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PointsProcessor extends Processor<PointsMessageData> {

    public PointsProcessor() {super(PointsMessageData.class);}

    @Override
    public void process(final String message) {
        super.process(message);
    }

    @Override
    protected Event buildEvent(final PointsMessageData pointsMessageData) {
        return new Event("device.points.status", pointsMessageData.getSerial());
    }

}
