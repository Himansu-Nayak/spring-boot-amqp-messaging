package com.org.spring.boot.amqp.message.bridge.panelInfo;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Info implements Serializable {
    private String login;
    private String ctime;
}