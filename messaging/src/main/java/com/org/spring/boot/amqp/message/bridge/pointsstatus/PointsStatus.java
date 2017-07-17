package com.org.spring.boot.amqp.message.bridge.pointsstatus;

import com.org.spring.boot.amqp.message.Message;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PointsStatus implements Message {
    private PointsData data;
}