package com.org.spring.boot.amqp.processor;

import com.jayway.restassured.path.json.JsonPath;
import com.org.spring.boot.amqp.processor.bridge.ActiveDevicesCountProcessor;
import com.org.spring.boot.amqp.processor.bridge.AlarmStatusProcessor;
import com.org.spring.boot.amqp.processor.bridge.PanelInfoProcessor;
import com.org.spring.boot.amqp.processor.bridge.PointsStatusProcessor;
import com.org.spring.boot.amqp.processor.bridge.TroubleStatusProcessor;
import com.org.spring.boot.amqp.processor.tenant.PointsProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProcessorAdapter {

    @Autowired
    private PointsProcessor pointsProcessor;

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

    public void process(final Message message) {
        final String jsonMessage = new String(message.getBody());
        final String event = searchJson(jsonMessage, "event");
        if (event.equalsIgnoreCase("points")) {
            pointsProcessor.process(jsonMessage);
        } else if (event.equalsIgnoreCase("points.finished")) {
            pointsStatusProcessor.process(jsonMessage);
        } else if (event.equalsIgnoreCase("panelinfo")) {
            panelInfoProcessor.process(jsonMessage);
        } else if (event.equalsIgnoreCase("activedevices")) {
            activeDevicesCountProcessor.process(jsonMessage);
        } else if (event.contains("alarm")) {
            alarmStatusProcessor.process(jsonMessage);
        } else if (event.contains("trouble")) {
            troubleStatusProcessor.process(jsonMessage);
        }

    }

    public String searchJson(final String json, final String key) {
        return JsonPath.from(json).get(key);
    }

}
