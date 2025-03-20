package com.chattycathy.client.handler;

import com.chattycathy.client.handler.commands.Command;
import com.chattycathy.client.handler.commands.Exit;

import java.util.ArrayList;
import java.util.List;

public class CommandList {

    private CommandList() {}

    /**
     * This method returns a list of valid commands for the runtime of the programme.
     * @return List of valid commands.
     */
    public static List<Command> getCommandList() {
        return new ArrayList<>(List.of(
                new Exit()
        ));
    }

}
