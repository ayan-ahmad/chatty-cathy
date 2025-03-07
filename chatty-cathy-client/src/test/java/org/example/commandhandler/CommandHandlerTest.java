package org.example.commandhandler;

import org.example.commandhandler.commands.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandHandlerTest {
    Command[] commandList = {
            new StubCommand()
    };

    @Test
    void noCommandInputted() {
        CommandHandler item = new CommandHandler(commandList);
        String output = item.runCommand("Hello");
        assertNull(output);
    }

    @Test
    void invalidCommandInputted() {
        CommandHandler item = new CommandHandler(commandList);
        String output = item.runCommand("/Hello");
        assertEquals("'/Hello' is not a valid command.", output);
    }

    @Test
    void validCommandInputted() {
        CommandHandler item = new CommandHandler(commandList);
        String output = item.runCommand("/stub");
        assertEquals("'/stub' has concluded successfully.", output);
    }

    @Test
    void validCommandInputtedWithInvalidParameters() {
        CommandHandler item = new CommandHandler(commandList);
        assertThrows(IllegalArgumentException.class, ()-> item.runCommand("/stub with other info"));
    }
}

