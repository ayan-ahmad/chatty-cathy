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
                "Valid Commands:\n" +
                "/help - Returns information on each command.\n" +
                "/stub - Stub of a command for testing." +
                System.lineSeparator();

        // removes log info as it changes based one time ran.
        String actualOutputParsed = outputStream.toString().split(" -- ")[1];

        assertEquals(expectedOutput, actualOutputParsed);
        assertEquals("'/help' has concluded successfully.", output);
    }
}