package com.org.spring.boot.amqp.message.bridge.devices;

import com.org.spring.boot.amqp.message.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActiveDevicesCountData extends Data {
    private Info info;
}
