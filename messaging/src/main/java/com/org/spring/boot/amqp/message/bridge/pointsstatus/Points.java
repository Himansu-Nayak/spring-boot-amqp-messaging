package com.org.spring.boot.amqp.message.bridge.pointsstatus;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Points {
    private String address;
    private String deviceName;
    private String deviceDescription;
    private ExtraInfo extraInfo;
}