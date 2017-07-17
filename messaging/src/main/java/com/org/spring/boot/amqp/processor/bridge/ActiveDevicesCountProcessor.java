package com.org.spring.boot.amqp.processor.bridge;

import com.org.spring.boot.amqp.message.Message;
import com.org.spring.boot.amqp.message.bridge.devices.ActiveDevicesCount;
import com.org.spring.boot.amqp.processor.Processor;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class ActiveDevicesCountProcessor extends Processor {

    public void processMessage(final Message message) {
        ActiveDevicesCount activeDevicesCount = (ActiveDevicesCount) message;
        System.out.println(activeDevicesCount);
        String jsonMessage = null;
        try {
            if (activeDevicesCount != null) {
                activeDevicesCount.getData().setSource("Inspection Service");
                jsonMessage = new ObjectMapper().writeValueAsString(activeDevicesCount);
            }
        } catch (final IOException ioException) {
            log.error("Exception during parsing active devices count ", ioException);
        }
        inspectionMessagePublisher.sendMessage(jsonMessage);
    }

}
