package org.example.commandhandler.commands;

import java.util.List;

public class Help implements Command {

    private final Command[] commandList;

    /**
     * Defines the list of commands /help describes.
     * @param commandList is the list of command types that will be shown in the /help,
     *                    this is passed in so that it can be customised.
     */
    public Help(Command[] commandList) {
        this.commandList = commandList;
    }

    /**
     * Overload for Help, this is so Help() can be displayed within /help.
     */
    public Help() {
        this.commandList = new Command[]{};
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
     * @throws IllegalArgumentException if the dev tries to execute without adding items to display.
     */
    @Override
    public void execute(List<String> parameter) {
        if (this.commandList.length > 0) {
            StringBuilder helpInfo = new StringBuilder();
            for (Command command : this.commandList) {
                helpInfo.append(command.getDescription()).append("\n");
            }
            System.out.println(helpInfo);
        }
        else {
            throw new IllegalArgumentException(
                    this.getName() + " must receive a list of Command types to display correctly."
            );
        }
    }
}
