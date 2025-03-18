package com.chattycathy.server.message_logging;

import com.chattycathy.server.model.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.IOException;

@Slf4j
@AllArgsConstructor
public class MessageLog {

    /**
     * holds path to message log file
     */
    String messageLogPath;

    /**
     * Appends a message to log file
     * @param message message logged
     */
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
