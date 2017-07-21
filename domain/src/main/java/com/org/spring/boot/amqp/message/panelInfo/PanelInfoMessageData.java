package com.org.spring.boot.amqp.message.panelInfo;


import com.org.spring.boot.amqp.message.Message;
import com.org.spring.boot.amqp.message.MessageData;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PanelInfoMessageData extends MessageData implements Message {
    private Map<String, String> info;
}
