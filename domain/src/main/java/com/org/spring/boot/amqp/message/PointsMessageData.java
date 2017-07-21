package com.org.spring.boot.amqp.message;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PointsMessageData extends MessageData implements Message {
    private List<Points> points;

}
