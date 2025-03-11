package com.chattycathy.client.handler;

import com.chattycathy.client.handler.commands.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommandHandlerTest {
    List<Command> commandList = new ArrayList<>(List.of(
            new StubCommand()
    ));

    @Test
    void noCommandInput() {
        CommandHandler item = new CommandHandler(commandList);
        String output = item.runCommand("Hello");
        assertNull(output);
    }

    @Test
    void invalidCommandInput() {
        CommandHandler item = new CommandHandler(commandList);
        String exampleOutput = item.runCommand("/Hello");
        assertEquals("'/Hello' is not a valid command.", exampleOutput);
    }

    @Test
    void invalidCommandInputJustChar() {
        CommandHandler item = new CommandHandler(commandList);
        String singleCharOutput = item.runCommand("/");
        assertEquals("'/' is not a valid command.", singleCharOutput);
    }

    @Test
    void invalidCommandInputJustCharWithSpace() {
        CommandHandler item = new CommandHandler(commandList);
        String charOutputWithSpace = item.runCommand("/ ");
        assertEquals("'/ ' is not a valid command.", charOutputWithSpace);
    }

    @Test
    void validCommandInput() {
        CommandHandler item = new CommandHandler(commandList);
        String output = item.runCommand("/stub");
        assertEquals("'/stub' has concluded successfully.", output);
    }

    @Test
    void validCommandInputWithInvalidParameters() {
        CommandHandler item = new CommandHandler(commandList);
        assertThrows(IllegalArgumentException.class, ()-> item.runCommand("/stub with other info"));
    }

    @Test
    void duplicateCommandListInput() {
        List<Command> commandListEx = new ArrayList<>(List.of(
                new StubCommand(),
                new StubCommand()
        ));
        assertThrows(IllegalArgumentException.class, ()-> new CommandHandler(commandListEx));
    }

    @Test
    void duplicateHelpInput() {
        List<Command> commandListEx = new ArrayList<>(List.of(
                new Help(new ArrayList<>()),
                new StubCommand()
        ));
        assertThrows(IllegalArgumentException.class, ()-> new CommandHandler(commandListEx));
    }

    @Test
    void emptyCommandListInput() {
        List<Command> commandListEx = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, ()-> new CommandHandler(commandListEx));
    }

    @Test
    void duplicateHelpListInput() {
        List<Command> commandListEx = new ArrayList<>(List.of(
                new StubCommand(),
                new Help(new ArrayList<>())
        ));
        assertThrows(IllegalArgumentException.class, ()-> new CommandHandler(commandListEx));
    }

    @Test
    void multipleValuesNoDuplicates() {
        List<Command> commandListEx = new ArrayList<>(List.of(
                new StubCommand(),
                new Exit()
        ));
        CommandHandler item = new CommandHandler(commandListEx);
        String output = item.runCommand("/stub");
        assertEquals("'/stub' has concluded successfully.", output);
    }

}

