package com.chattycathy.client.handler.commands;

import com.chattycathy.client.model.User;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Friend implements Command {
    public Friend() {
    }

    /**
     * This method gets the name of the command.
     * @return the string needed as an input to run the command.
     */
    @Override
    public String getName() {
        return "/friend";
    }

    /**
     * informative message about the commands purpose and how to use it.
     * @return string describing the command.
     */
    @Override
    public String getDescription() {
        return String.format("%s @[username]- Adds [username] to friends list", this.getName());
    }

    /**
     * This method executes the command, adding the user to the friends list.
     * @param parameter is a list of strings,
     *                  representing further inputs that can be used for commands.
     *                  for this command parameter should be a username
     */
    @Override
    public void execute(List<String> parameter) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("src/main/resources/friendsList.txt", true))) {
            writer.println(parameter.getFirst());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
