package com.chattycathy.server.model;

import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.IOException;

@Slf4j
public class MessageLog {
    String messageLogPath;

    public MessageLog(String messageLogPath) {
        this.messageLogPath = messageLogPath;
    }

    public void logMessage(Message message) {

        try {
            FileWriter fileWriter = new FileWriter(messageLogPath, true);

            fileWriter.write(message.toString() + "\n");

            fileWriter.close();
        } catch (IOException e) {
            log.error("couldn't log message: {}", message);
        }
    }
}
