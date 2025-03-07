package org.example.commandframework;
import org.example.commandframework.commands.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Expandable framework for detecting and executing commands based on user input.
 */
public class CommandFramework {
    Command[] commandList;

    /**
     * Establishes list of valid commands.
     * @param commandList is used to determine the list of valid commands.
     *                    this is a parameter so that new command types can
     *                    be added without needing to edit this file
     *
     */
    public CommandFramework(Command[] commandList) {
        this.commandList = commandList;
    }

    /**
     * separates the commands name from its parameters
     * @param userInput is a string typically in the format
     *                  /[command] [parameter1] [parameter2] ...etc
     * @return parsed command name from user input.
     */
    private String parseCommandName(String userInput) {
        String[] temp = userInput.split(" ");
        return temp[0];
    }

    /**
     * separates the parameters from its name
     * @param userInput is a string typically in the format
     *                  /[command] [parameter1] [parameter2] ...etc
     * @return list of strings representing parameters,
     * returns an empty list if none are given.
     */
    private List<String> parseCommandParameters(String userInput) {
        String[] temp = userInput.split(" ");
        if (temp.length > 1) {
            return new ArrayList<>(Arrays.asList(temp).subList(1, temp.length));
        }
        else {
            return Collections.emptyList();
        }
    }

    /**
     * checks for valid command and executes it if so.
     * @param userInput is a string, if the string is marked as a command with a '/'
     *                  symbol it will be checked and executed. else it is skipped.
     * @return a string describing if a valid command has been passed,
     * if a command encounters an error while executing, an exception is thrown.
     *
     * if a command is not passed in, null is returned.
     */
    public String runCommand(String userInput) {
        if (!userInput.isEmpty() && userInput.charAt(0) == '/') {
            String commandName = parseCommandName(userInput);
            List<String> parameter = parseCommandParameters(userInput);

            for (Command command: this.commandList) {
                if (commandName.equals(command.getName())) {
                    command.execute(parameter);
                    return String.format("'%s' has concluded successfully.", userInput);
                }
            }
            return String.format("'%s' is not a valid command.", userInput);
        }
        return null;
    }
}
