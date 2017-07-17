package com.org.spring.boot.amqp.message.bridge.devices;

import com.org.spring.boot.amqp.message.Message;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActiveDevicesCount implements Message {
    private ActiveDevicesCountData data;
}
