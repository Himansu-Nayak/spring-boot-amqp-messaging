package com.org.spring.boot.amqp.processor.bridge;

import com.org.spring.boot.amqp.message.alarm.AlarmStatusMessageData;
import com.org.spring.boot.amqp.processor.Event;
import com.org.spring.boot.amqp.processor.Processor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AlarmStatusProcessor extends Processor<AlarmStatusMessageData> {

    public AlarmStatusProcessor() {
        super(AlarmStatusMessageData.class);
    }

    @Override
    public void process(final String message) {
        super.process(message);
    }

    @Override
    protected Event buildEvent(final AlarmStatusMessageData alarmStatusMessageData) {
        return new Event("device.alarm.status", alarmStatusMessageData.getSerial());
    }

}
