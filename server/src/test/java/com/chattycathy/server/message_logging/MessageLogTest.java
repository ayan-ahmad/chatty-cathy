package com.chattycathy.server.message_logging;

import com.chattycathy.server.model.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class MessageLogTest {
    MessageLog messageLog;

    @BeforeEach
    void setUp() {


        try {
            FileWriter fileWriter = new FileWriter(messageLogPath);

            fileWriter.write("");

            fileWriter.close();
        } catch (IOException e) {
            fail();
        }

        messageLog = new MessageLog(messageLogPath);
    }
    @Test
    void logNormalMessage() {
        String userName = "bella";
        String content = "why hello there";

        Message message = new Message(userName, content);


        messageLog.logMessage(message);

        assertMessagesLogged(List.of(message));
    }

    @Test
    void logThreeNormalMessages() {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("bella", "hi hru"));
        messages.add(new Message("waqar", "ok n u"));
        messages.add(new Message("bella", "ye ok"));

        for (Message message: messages) {
            messageLog.logMessage(message);
        }

        assertMessagesLogged(messages);
    }

    private void assertMessagesLogged(List<Message> messages) {
        try {
            File messageLogFile = new File(messageLogPath);

            Scanner scanner = new Scanner(messageLogFile);

            for (Message message: messages) {
                assertEquals(message.toString(), scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private final String messageLogPath = "src/test/resources/messages.log";
}
