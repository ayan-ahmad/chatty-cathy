package com.chattycathy.client.handler.commands;
import java.util.List;


public class Exit implements Command {
    /**
     * This method gets the name of the command.
     * @return the string needed as an input to run the command.
     */
    @Override
    public String getName() {
        return "/exit";
    }

    /**
     * informative message about the commands purpose and how to use it.
     * @return string describing the command.
     */
    @Override
    public String getDescription() {
        return String.format("%s - Closes the client.", this.getName());
    }

    /**
     * This method executes the command, closing the programme.
     *
     * @param parameter is a list of strings,
     *                  representing further inputs that can be used for commands.
     *                  for this command parameter should be null
     */
    @Override
    public void execute(List<String> parameter) {
        System.exit(0);
    }
}

