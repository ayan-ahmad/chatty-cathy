package com.chattycathy.client.handler;
import com.chattycathy.client.handler.commands.Command;

import java.util.Collections;
import java.util.List;

public class StubCommand implements Command {
    @Override
    public String getName() {
        return "/stub";
    }

    @Override
    public String getDescription() {
        return String.format("%s - Stub of a command for testing.", this.getName());
    }

    @Override
    public void execute(List<String> parameter) {
        if (!parameter.equals(Collections.emptyList())) {
            throw new IllegalArgumentException(
                    String.format("'%s' Parameters have been inputted incorrectly.", this.getName())
            );
        }
    }
}
