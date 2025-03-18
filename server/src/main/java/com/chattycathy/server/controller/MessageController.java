package com.chattycathy.server.controller;

import com.chattycathy.server.model.Message;
import com.chattycathy.server.message_logging.MessageLog;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Controller;

/**
 * Contains endpoints and responses for the websocket
 */
@Controller
@Slf4j
@AllArgsConstructor
public class MessageController {

    MessageLog messageLog;

    /**
     * Logs then returns and sends a message to all clients
     * @param message This is the message that is sent by a client and logged then received on server-side
     * @return We return a message of our choice, since this is an app destination this will be sent to everyone
     * @see com.chattycathy.server.config.WebSocketConfig#configureMessageBroker(MessageBrokerRegistry)
     * WebSocketConfig.configureMessageBroker contains the config/routing for this endpoint
     */
    @MessageMapping("/main")
    @SendTo("/topic/main")
    public Message main(Message message) {
        messageLog.logMessage(message);

        return message;
    }
}
