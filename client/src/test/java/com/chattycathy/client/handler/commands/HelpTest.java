package com.chattycathy.client.handler.commands;

import com.chattycathy.client.handler.*;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HelpTest {
    @Test
    void validCommandInput() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        List<Command> commandList = new ArrayList<>(List.of(
                new StubCommand()
        ));
        CommandHandler item = new CommandHandler(commandList);
        String output = item.runCommand("/help");

        String expectedOutput =
                "/help - Returns information on each command.\n" +
                "/stub - Stub of a command for testing.\n" +
                System.lineSeparator();

        assertEquals(expectedOutput, outputStream.toString());
        assertEquals("'/help' has concluded successfully.", output);
    }
}