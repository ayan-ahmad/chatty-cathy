package com.chattycathy.client.handler;

import com.chattycathy.client.model.Model;
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
     */
    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        log.info("Connected to WebSocket server");
        log.debug("Connected successfully to session {}, headers: {}", session, connectedHeaders);
        Scanner s = new Scanner(System.in);

        session.subscribe("/topic/ping", this);

        Model model = new Model("Client", "Hi Chatty Cathy Server");

        session.send("/app/ping", model);

        while (session.isConnected()) {
            Message message = new Message(s.nextLine());
            if (!message.getMessage().isEmpty()) {
                session.send("/app/ping", message);
            }
        }
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
        if (payload instanceof Model model) {
            log.info("<{}>: {}", model.getName(), model.getMessage());
        }
    }

    /**
     * Provides the type of payload this handler will deal with
     */
    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Model.class;
    }
}