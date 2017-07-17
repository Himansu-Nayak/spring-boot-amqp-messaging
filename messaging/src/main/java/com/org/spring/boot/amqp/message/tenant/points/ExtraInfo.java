package com.org.spring.boot.amqp.message.tenant.points;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtraInfo {
    @JsonProperty("DT")
    private String DT;
    @JsonProperty("PT")
    private String PT;
    @JsonProperty("CVAL")
    private String CVAL;
    @JsonProperty("U")
    private String U;
    @JsonProperty("C")
    private String C;
    @JsonProperty("T")
    private String T;
    @JsonProperty("D")
    private String D;
}
