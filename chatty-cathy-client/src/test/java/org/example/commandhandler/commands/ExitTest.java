package org.example.commandhandler.commands;

import org.example.commandhandler.CommandHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


class ExitTest {
    Command[] commandList = {
            new Exit(),
    };

    @Test
    void validExitWithInvalidParameters() {
        CommandHandler item = new CommandHandler(commandList);
        assertThrows(IllegalArgumentException.class, ()-> item.runCommand("/exit area"));
    }

    // Valid exit command cannot be automatically tested due to the nature of the command.
}