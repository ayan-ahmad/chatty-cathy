package org.example.commandframework.commands;

import java.util.List;

/**
 * Used as a command template, getName() returns the string needed to run the command.
 * execute() runs the command, taking a list of parameters to be used for further user input.
 */
public interface Command {
    String getName();
    void execute(List<String> parameter);
}