package com.chattycathy.client.handler.commands;

import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
public class Help implements Command {

    private final List<Command> commandList;

    /**
     * Defines the list of commands /help describes.
     * @param commandList is the list of command types that will be shown in the /help,
     *                    this is passed in so that it can be customised.
     */
    public Help(List<Command> commandList) {
        this.commandList = commandList;
    }

    /**
     * This method gets the name of the command.
     * @return the string needed as an input to run the command.
     */
    @Override
    public String getName() {
        return "/help";
    }

    /**
     * informative message about the commands purpose and how to use it.
     * @return string describing the command.
     */
    @Override
    public String getDescription() {
        return String.format("%s - Returns information on each command.", this.getName());
    }

    /**
     * This method executes the command, logs a string describing each command in the list.
     * @param parameter is a list of strings,
     *                  representing further inputs that can be used for commands.
     *                  for this command parameter should be null
     */
    @Override
    public void execute(List<String> parameter) {
        StringBuilder helpInfo = new StringBuilder();
        helpInfo.append("Valid Commands:\n");
        for (Command command : this.commandList) {
            helpInfo.append(command.getDescription()).append("\n");
        }
        helpInfo.delete(helpInfo.length() - 1, helpInfo.length()); // removes last newline
        log.info(helpInfo.toString());
    }
}
