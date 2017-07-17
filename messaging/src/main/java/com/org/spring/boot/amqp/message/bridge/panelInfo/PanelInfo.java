package com.org.spring.boot.amqp.message.bridge.panelInfo;

import com.org.spring.boot.amqp.message.Message;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PanelInfo implements Message {
    private PanelInfoData data;
}
