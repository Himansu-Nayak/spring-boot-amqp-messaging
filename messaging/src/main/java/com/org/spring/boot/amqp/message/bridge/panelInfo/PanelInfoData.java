package com.org.spring.boot.amqp.message.bridge.panelInfo;

import com.org.spring.boot.amqp.message.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PanelInfoData extends Data {
    private Info info;
}
