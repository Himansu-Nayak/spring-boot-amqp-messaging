package com.org.spring.boot.amqp.message.bridge.alarm;

import com.org.spring.boot.amqp.message.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlarmStatusData extends Data {
    private String pointAddress;
}
