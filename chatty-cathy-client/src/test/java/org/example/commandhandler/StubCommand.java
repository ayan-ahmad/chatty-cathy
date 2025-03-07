package org.example.commandhandler;
import org.example.commandhandler.commands.*;

import java.util.Collections;
import java.util.List;

public class StubCommand implements Command {
    @Override
    public String getName() {
        return "/stub";
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
