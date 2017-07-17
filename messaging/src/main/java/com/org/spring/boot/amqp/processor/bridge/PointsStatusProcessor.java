package com.org.spring.boot.amqp.processor.bridge;

import com.org.spring.boot.amqp.message.Message;
import com.org.spring.boot.amqp.message.bridge.pointsstatus.PointsStatus;
import com.org.spring.boot.amqp.processor.Processor;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class PointsStatusProcessor extends Processor {

    @Override
    public void processMessage(final Message message) {
        PointsStatus pointsStatus = (PointsStatus) message;
        System.out.println(pointsStatus);
        String jsonMessage = null;
        try {
            if (pointsStatus != null) {
                pointsStatus.getData().setSource("Inspection Service");
                jsonMessage = new ObjectMapper().writeValueAsString(pointsStatus);
            }
        } catch (final IOException ioException) {
            log.error("Exception during parsing panel points status ", ioException);
        }
        inspectionMessagePublisher.sendMessage(jsonMessage);
    }

}
