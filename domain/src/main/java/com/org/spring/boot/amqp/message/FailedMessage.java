package com.org.spring.boot.amqp.message;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FailedMessage {

    private String messageId;
    private String originSource;
    private String errorSource;
    private String message;
    private String errorDetails;
    private Date creationDate;

}
