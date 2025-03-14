package com.chattycathy.client.handler;

import com.chattycathy.client.handler.commands.*;
import com.chattycathy.client.model.Message;
import com.chattycathy.client.model.User;
import io.micrometer.common.lang.NonNullApi;
import io.micrometer.common.lang.Nullable;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.lang.reflect.Type;

/**
 * A custom session handler which deals with: subscribing, sending and listening to topics on the server
 */
@NonNullApi
@Component
@Slf4j
public class ClientStompSessionHandler extends StompSessionHandlerAdapter {
    @Setter
    User user;

    Scanner scanner;

    public ClientStompSessionHandler(Scanner scanner) {
        this.scanner = scanner;
    }
    /**
     * A custom implementation that runs after connecting that subscribes to the needed events
     * and handles message sending
     * Also checks if user message is a command, handling it if so.
     */
    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        log.info("Connected to Chatty Cathy");
        log.debug("Connected successfully to session {}, headers: {}", session, connectedHeaders);
        log.info("Type '/help' to See The List of Available Commands");
        log.info("Please type to chat!");

        session.subscribe("/topic/main", this);

        new Thread(() -> {

            List<Command> commandList = getComandList();
            CommandHandler commandHandler = new CommandHandler(commandList);
            String commandReturn;

            while (session.isConnected()) {
                Message message = new Message(user.getUserName(), scanner.nextLine());
                commandReturn = commandHandler.runCommand(message.getMessage());

                if (!message.getMessage().isEmpty() && commandReturn == null) {
                    session.send("/app/main", message);
                } else if (commandReturn != null) {
                    log.info(commandReturn);
                }
            }
            scanner.close();
        }).start();
    }


    /**
     * This method returns a list of valid commands for the runtime of the programme.
     * @return List of valid commands.
     */
    private List<Command> getComandList() {
        return new ArrayList<>(List.of(
                new Exit()
        ));
    }


    /**
     * A custom implementation that handles errors when the payload converts
     */
    @Override
    public void handleException(StompSession session, @Nullable StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        log.error("Error: {}", exception.getMessage());
    }

    /**
     * A custom implementation that handles incoming messages
     */
    @Override
    public void handleFrame(StompHeaders headers, @Nullable Object payload) {
        if (payload instanceof Message message) {
            log.info("{}: {}",message.getUserName(), message.getMessage());
        }
    }

    /**
     * Provides the type of payload this handler will deal with
     */
    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Message.class;
    }
}