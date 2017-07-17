package com.org.spring.boot.amqp.message.bridge.devices;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Info implements Serializable {
    private int total;
}