package com.org.spring.boot.amqp.message.bridge.alarm;

import com.org.spring.boot.amqp.message.Message;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlarmStatus implements Message {
    private AlarmStatusData data;
}
