package com.chattycathy.client.handler;

import com.chattycathy.client.model.Message;
import io.micrometer.common.lang.NonNullApi;
import io.micrometer.common.lang.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;
import java.util.Scanner;

import java.lang.reflect.Type;

/**
 * A custom session handler which deals with: subscribing, sending and listening to topics on the server
 */
@NonNullApi
@Component
@Slf4j
public class ClientStompSessionHandler extends StompSessionHandlerAdapter {

    /**
     * A custom implementation that runs after connecting that subscribes to the needed events
     * and handles message sending
     */
    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        log.info("Connected to Chatty Cathy");
        log.debug("Connected successfully to session {}, headers: {}", session, connectedHeaders);


        session.subscribe("/topic/main", this);

        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);

            log.info("Please enter a username: ");

            String userName;
            do {
                userName = scanner.nextLine();
            } while (userName.isEmpty());

            log.info("Please type to chat!");
            while (session.isConnected()) {
                Message message = new Message(userName, scanner.nextLine());
                if (!message.getMessage().isEmpty()) {
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
            log.info("{}", message.getMessage());
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