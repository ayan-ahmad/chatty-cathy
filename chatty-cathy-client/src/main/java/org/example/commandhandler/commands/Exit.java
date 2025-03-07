package org.example.commandhandler.commands;

import java.util.Collections;
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
     * This method executes the command, and throws an exception if it fails
     *
     * @param parameter is a list of strings,
     *                  representing further inputs that can be used for commands.
     *                  for this command parameter should be null
     */
    @Override
    public void execute(List<String> parameter) {
        if (parameter.equals(Collections.emptyList())) {
            System.exit(0);
        }
        else {
            throw new IllegalArgumentException(
                    String.format("'%s': Parameters have been input incorrectly.", this.getName())
            );
        }
    }
}

