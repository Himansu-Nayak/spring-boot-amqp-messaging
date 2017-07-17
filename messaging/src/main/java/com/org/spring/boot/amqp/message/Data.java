package com.org.spring.boot.amqp.message;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@lombok.Data
public class Data {
    private String messageId;
    private String source;
    private String panelId;
    private String serial;
    private String event;
}
