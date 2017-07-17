package com.org.spring.boot.amqp.processor.bridge;

import com.org.spring.boot.amqp.message.Message;
import com.org.spring.boot.amqp.message.bridge.alarm.AlarmStatus;
import com.org.spring.boot.amqp.processor.Processor;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class AlarmStatusProcessor extends Processor {

    public void processMessage(final Message message) {
        AlarmStatus alarmStatus = (AlarmStatus) message;
        System.out.println(alarmStatus);
        String jsonMessage = null;
        try {
            if (alarmStatus != null) {
                alarmStatus.getData().setSource("Inspection Service");
                jsonMessage = new ObjectMapper().writeValueAsString(alarmStatus);
            }
        } catch (final IOException ioException) {
            log.error("Exception during parsing alarm status ", ioException);
        }
        inspectionMessagePublisher.sendMessage(jsonMessage);
    }

}
