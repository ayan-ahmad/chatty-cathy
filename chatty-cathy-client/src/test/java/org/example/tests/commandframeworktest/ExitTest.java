package org.example.tests.commandframeworktest;

import org.example.commandframework.CommandFramework;
import org.example.commandframework.commands.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


class ExitTest {
    Command[] commandList = {
            new Exit(),
    };

    @Test
    void validExitWithInvalidParameters() {
        CommandFramework item = new CommandFramework(commandList);
        assertThrows(IllegalArgumentException.class, ()-> item.runCommand("/exit area"));
    }

    // Valid exit command cannot be automatically tested due to the nature of the command.
}