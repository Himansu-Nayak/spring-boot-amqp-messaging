package com.org.spring.boot.amqp.processor;

import com.jayway.restassured.path.json.JsonPath;
import com.org.spring.boot.amqp.message.bridge.alarm.AlarmStatus;
import com.org.spring.boot.amqp.message.bridge.devices.ActiveDevicesCount;
import com.org.spring.boot.amqp.message.bridge.panelInfo.PanelInfo;
import com.org.spring.boot.amqp.message.bridge.pointsstatus.PointsStatus;
import com.org.spring.boot.amqp.message.bridge.trouble.TroubleStatus;
import com.org.spring.boot.amqp.message.tenant.points.PanelPoints;
import com.org.spring.boot.amqp.processor.bridge.ActiveDevicesCountProcessor;
import com.org.spring.boot.amqp.processor.bridge.AlarmStatusProcessor;
import com.org.spring.boot.amqp.processor.bridge.PanelInfoProcessor;
import com.org.spring.boot.amqp.processor.bridge.PointsStatusProcessor;
import com.org.spring.boot.amqp.processor.bridge.TroubleStatusProcessor;
import com.org.spring.boot.amqp.processor.tenant.PanelPointsProcessor;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class ProcessorAdapter {

    @Autowired
    private PanelPointsProcessor panelPointsProcessor;

    @Autowired
    private PointsStatusProcessor pointsStatusProcessor;

    @Autowired
    private PanelInfoProcessor panelInfoProcessor;

    @Autowired
    private ActiveDevicesCountProcessor activeDevicesCountProcessor;

    @Autowired
    private AlarmStatusProcessor alarmStatusProcessor;

    @Autowired
    private TroubleStatusProcessor troubleStatusProcessor;

    public void process(final Message queueMessage) {
        final String jsonMessage = new String(queueMessage.getBody());
        final String event = searchJson(jsonMessage, "data.event");
        try {
            //TODO Cleanup the hardcode events.
            if (event.contains("points")) {
                panelPointsProcessor.processMessage(objectMapper().readValue(jsonMessage, PanelPoints.class));
            } else if (event.equalsIgnoreCase("points.finished")) {
                pointsStatusProcessor.processMessage(objectMapper().readValue(jsonMessage, PointsStatus.class));
            } else if (event.equalsIgnoreCase("panelinfo")) {
                panelInfoProcessor.processMessage(objectMapper().readValue(jsonMessage, PanelInfo.class));
            } else if (event.equalsIgnoreCase("activedevices")) {
                activeDevicesCountProcessor.processMessage(objectMapper().readValue(jsonMessage, ActiveDevicesCount.class));
            } else if (event.contains("alarm")) {
                alarmStatusProcessor.processMessage(objectMapper().readValue(jsonMessage, AlarmStatus.class));
            } else if (event.contains("trouble")) {
                troubleStatusProcessor.processMessage(objectMapper().readValue(jsonMessage, TroubleStatus.class));
            }
        } catch (final IOException ioException) {
            log.error("Exception during unmarshalling ", ioException);
        }
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    public String searchJson(final String json, final String key) {
        return JsonPath.from(json).get(key);
    }

}
