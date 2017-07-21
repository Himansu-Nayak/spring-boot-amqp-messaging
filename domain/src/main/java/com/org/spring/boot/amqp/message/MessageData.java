package com.org.spring.boot.amqp.message;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageData {

    @NotNull
    private String messageId;

    @NotNull
    private String source;

    @NotNull
    private String panelId;

    @NotNull
    private String serial;

    @NotNull
    private String event;
}
