package com.org.spring.boot.amqp.processor.tenant;

import com.org.spring.boot.amqp.message.Message;
import com.org.spring.boot.amqp.message.tenant.points.PanelPoints;
import com.org.spring.boot.amqp.processor.Processor;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class PanelPointsProcessor extends Processor {

    @Override
    public void processMessage(final Message message) {
        PanelPoints panelPoints = (PanelPoints) message;
        System.out.println(panelPoints);
        String jsonMessage = null;
        try {
            if (panelPoints != null) {
                panelPoints.getData().setSource("Inspection Service");
                jsonMessage = new ObjectMapper().writeValueAsString(panelPoints);
            }
        } catch (final IOException ioException) {
            log.error("Exception during parsing panel points ", ioException);
        }
        inspectionMessagePublisher.sendMessage(jsonMessage);
    }

}
