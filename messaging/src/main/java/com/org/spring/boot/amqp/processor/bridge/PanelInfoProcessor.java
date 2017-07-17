package com.org.spring.boot.amqp.processor.bridge;

import com.org.spring.boot.amqp.message.Message;
import com.org.spring.boot.amqp.message.bridge.panelInfo.PanelInfo;
import com.org.spring.boot.amqp.processor.Processor;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class PanelInfoProcessor extends Processor {

    public void processMessage(final Message message) {
        PanelInfo panelInfo = (PanelInfo) message;
        System.out.println(panelInfo);
        String jsonMessage = null;
        try {
            if (panelInfo != null) {
                panelInfo.getData().setSource("Inspection Service");
                jsonMessage = new ObjectMapper().writeValueAsString(panelInfo);
            }
        } catch (final IOException ioException) {
            log.error("Exception during parsing panel info ", ioException);
        }
        inspectionMessagePublisher.sendMessage(jsonMessage);
    }

}
