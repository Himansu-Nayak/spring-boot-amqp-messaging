package com.org.spring.boot.amqp.processor;

import lombok.Data;

@Data
public class Event {
    private String event;
    private String panelID;

    public Event(final String event, final String panelID) {
        this.event = event;
        this.panelID = panelID;
    }
}
