package com.chattycathy.client.handler;

import com.chattycathy.client.model.Model;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
@Slf4j
public class ClientStompSessionHandler extends StompSessionHandlerAdapter {

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        log.info("Connected to WebSocket server");
        log.debug("Connected successfully to session {}, headers: {}", session, connectedHeaders);

        session.subscribe("/topic/ping", this);

        Model model = new Model("Client", "Hi Chatty Cathy Server");

        session.send("/app/ping", model);
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        log.error("Error: {}", exception.getMessage());
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        if (payload instanceof Model model) {
            log.info("<{}>: {}", model.getName(), model.getMessage());
        }
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Model.class;
    }
}