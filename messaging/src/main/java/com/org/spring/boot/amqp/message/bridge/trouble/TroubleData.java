package com.org.spring.boot.amqp.message.bridge.trouble;

import com.org.spring.boot.amqp.message.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TroubleData extends Data {
    private String pointAddress;
}
