package com.org.amqp.rabbit.message;

import java.io.Serializable;

public final class Message implements Serializable{

    private String text;
    private int priority;
    private boolean secret;

    // Default constructor is needed to deserialize JSON
    public Message() {
    }

    public Message(String text, int priority, boolean secret) {
        this.text = text;
        this.priority = priority;
        this.secret = secret;
    }

    public String getText() {
        return text;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isSecret() {
        return secret;
    }

    @Override
    public String toString() {
        return "Message{" +
                "text='" + text + '\'' +
                ", priority=" + priority +
                ", secret=" + secret +
                '}';
    }
}
