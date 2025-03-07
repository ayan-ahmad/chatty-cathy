package org.example;

import java.io.Serial;
import java.io.Serializable;

public class Message implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String messageContent;
    private final String username;

    public Message(String messageContent, String username) {
        this.messageContent = messageContent;
        this.username = username;
    }

    public String getMessage() {
        return messageContent;
    }

    public String getUsername() {
        return username;
    }
}
