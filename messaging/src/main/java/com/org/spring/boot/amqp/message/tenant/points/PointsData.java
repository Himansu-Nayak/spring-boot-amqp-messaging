package com.org.spring.boot.amqp.message.tenant.points;

import com.org.spring.boot.amqp.message.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PointsData extends Data {
    private List<Points> points;

}
