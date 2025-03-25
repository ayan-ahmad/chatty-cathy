package com.chattycathy.client.handler;

import com.chattycathy.client.model.FriendList;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
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

    CommandHandler commandHandler;
    ArrayList<String> friendList;

    /**
     * A custom enum which adds codes to implement text styling in CLI
     */

    private enum Colour {
        RESET("\033[0m"),
        GREEN_BOLD("\033[1;32m");
        private final String code;

        Colour(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return code;
        }
    }

    public ClientStompSessionHandler(Scanner scanner, CommandHandler commandHandler) {
        this.scanner = scanner;
        this.commandHandler = commandHandler;
        FriendList friends = new FriendList();
        this.friendList = new ArrayList<String>();


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
        log.info("Type to create a post for your followers");

        session.subscribe("/topic/main", this);

        new Thread(() -> {
            while (session.isConnected()) {
                Message message = new Message(user.getUserName(), scanner.nextLine());
                String commandReturn = commandHandler.runCommand(message.getMessage());

                if (commandReturn != null) {
                    log.info(commandReturn);
                    if (Objects.equals(commandReturn.split(" ")[0], "'/friend'")) {
                        friendList.add(message.getMessage().split(" ")[1]);
                    }
                }
                else if (!message.getMessage().isEmpty()) {
                    session.send("/app/main", message);
                }
            }
            scanner.close();
        }).start();
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
            if (friendList.contains(message.getUserName())) {
                log.info(Colour.GREEN_BOLD + "{}: " + Colour.RESET + "{}",message.getUserName(), message.getMessage());
            }
            else {
                log.info("{}: {}", message.getUserName(), message.getMessage());
            }
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