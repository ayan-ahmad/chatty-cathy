package com.chattycathy.client.handler;
import com.chattycathy.client.handler.commands.*;

import java.util.*;


/**
 * Expandable framework for detecting and executing commands based on user input.
 */
public class CommandHandler {
    Map<String, Command> commandHashMap = new HashMap<>();

    /**
     * Establishes list of valid commands.
     * @param commandList is used to determine the list of valid commands.
     *                    this is a parameter so that new command types can
     *                    be added without needing to edit this file
     *
     * @throws IllegalArgumentException if entries are already in hashmap,
     * if no items are passed into the command list, or if Help specifically is passed in.
     * as Help is passed in automatically.
     */
    public CommandHandler(List<Command> commandList) {
        if (!commandList.isEmpty()) {
            commandList.addFirst(new Help(commandList));

            for(Command command : commandList) {
                if (commandHashMap.get(command.getName()) == null) {
                    commandHashMap.put(command.getName(), command);
                }
                else if (Objects.equals(command.getName(), "/help")) {
                    throw new IllegalArgumentException(command.getName() + " is already in use");
                }
                else {
                    throw new IllegalArgumentException("You must provide at least one command to CommandHandler");
                }
            }
        }
        else {
            throw new IllegalArgumentException("/help is automatically included in the command list.");
        }
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

            Command command = commandHashMap.get(commandName); // Returns null if not in hashmap
            if (command != null) {
                command.execute(parameter);
                return String.format("'%s' has concluded successfully.", command.getName());
            }
            else {
                return String.format("'%s' is not a valid command.", userInput);
            }
        }
        return null;
    }
}
