package com.chattycathy.server.controller;

import com.chattycathy.server.model.Model;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class MessageController {

    /**
     * @param message This is the message that is sent by a client and received on server-side
     * @return We return a message of our choice, since this is an app destination this will be sent to everyone
     * @see com.chattycathy.server.config.WebSocketConfig#configureMessageBroker(MessageBrokerRegistry)
     * WebSocketConfig.configureMessageBroker contains the config/routing for this endpoint
     */
    @MessageMapping("/ping")
    @SendTo("/topic/ping")
    public Model ping(Model message) {
        log.info("<{}>: {}", message.getName(), message.getMessage());
        return new Model("Server", "Welcome to Chatty Cathy!");
    }
}
