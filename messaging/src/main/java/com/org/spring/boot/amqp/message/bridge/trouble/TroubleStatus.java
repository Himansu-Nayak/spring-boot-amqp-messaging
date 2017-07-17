package com.org.spring.boot.amqp.message.bridge.trouble;

import com.org.spring.boot.amqp.message.Message;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TroubleStatus implements Message {
    private TroubleData data;
}
