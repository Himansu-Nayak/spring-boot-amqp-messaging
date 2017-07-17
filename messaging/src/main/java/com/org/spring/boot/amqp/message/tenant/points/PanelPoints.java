package com.org.spring.boot.amqp.message.tenant.points;

import com.org.spring.boot.amqp.message.Message;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PanelPoints implements Message {
    private PointsData data;
}