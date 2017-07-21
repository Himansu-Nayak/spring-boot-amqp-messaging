package com.org.spring.boot.amqp.message;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Points {
    private String address;
    private String deviceName;
    private String deviceDescription;
    private Map<String, String> extraInfo;
}