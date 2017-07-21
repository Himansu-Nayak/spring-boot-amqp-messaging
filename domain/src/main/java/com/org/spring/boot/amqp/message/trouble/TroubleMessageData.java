package com.org.spring.boot.amqp.message.trouble;

import com.org.spring.boot.amqp.message.Message;
import com.org.spring.boot.amqp.message.MessageData;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TroubleMessageData extends MessageData implements Message {
    private String pointAddress;
}
