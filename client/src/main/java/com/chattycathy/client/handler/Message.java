package com.chattycathy.client.handler;

/**
 * Expandable class for constructing message dataframe to transmit
 */
public class Message {
    String body;

    /**
     * Instantiates Message class with message body
     */
    public Message(String body) {
        this.body = body;
    }

    public String getMessage() {
        return this.body;
    }
}
