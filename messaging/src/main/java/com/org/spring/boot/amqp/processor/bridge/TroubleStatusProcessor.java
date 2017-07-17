package com.org.spring.boot.amqp.processor.bridge;

import com.org.spring.boot.amqp.message.Message;
import com.org.spring.boot.amqp.message.bridge.trouble.TroubleStatus;
import com.org.spring.boot.amqp.processor.Processor;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class TroubleStatusProcessor extends Processor {

    public void processMessage(final Message message) {
        TroubleStatus troubleStatus = (TroubleStatus) message;
        System.out.println(troubleStatus);
        String jsonMessage = null;
        try {
            if (troubleStatus != null) {
                troubleStatus.getData().setSource("Inspection Service");
                jsonMessage = new ObjectMapper().writeValueAsString(troubleStatus);
            }
        } catch (final IOException ioException) {
            log.error("Exception during parsing trouble status ", ioException);
        }
        inspectionMessagePublisher.sendMessage(jsonMessage);
    }

}