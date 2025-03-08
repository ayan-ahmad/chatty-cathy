package org.example.commandhandler.commands;

import org.example.commandhandler.CommandHandler;
import org.example.commandhandler.StubCommand;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class HelpTest {
    @Test
    void runningHelpWithoutPassingValues() {
        Command[] commandListInvalidHelp = {
                new Exit(),
                new Help()
        };
        CommandHandler item = new CommandHandler(commandListInvalidHelp);
        assertThrows(IllegalArgumentException.class, ()->
                item.runCommand("/help must receive a list of Command types to display correctly.")
        );
    }

    @Test
    void validCommandInput() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Command[] commandList = {
                new StubCommand(),
                new Help(new Command[] {
                        new Help(),
                        new StubCommand(),
                }),
        };
        CommandHandler item = new CommandHandler(commandList);
        String output = item.runCommand("/help");

        String expectedOutput =
                "/help - Returns information on each command.\n" +
                "/stub - Stub of a command for testing.\n" +
                System.lineSeparator();

        assertEquals(expectedOutput, outputStream.toString());
        assertEquals("'/help' has concluded successfully.", output);
    }

    @Test
    void duplicateCommandInput() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Command[] commandListDuplicate = {
            new StubCommand(),
            new Help(new Command[] {
                    new Help(),
                    new Help(),
                    new StubCommand(),
                    new StubCommand(),
            }),
        };

        CommandHandler item = new CommandHandler(commandListDuplicate);
        String output = item.runCommand("/help");

        String expectedOutput =
                "/help - Returns information on each command.\n" +
                "/help - Returns information on each command.\n" +
                "/stub - Stub of a command for testing.\n" +
                "/stub - Stub of a command for testing.\n" +
                System.lineSeparator();

        assertEquals(expectedOutput, outputStream.toString());
        assertEquals("'/help' has concluded successfully.", output);
    }
}