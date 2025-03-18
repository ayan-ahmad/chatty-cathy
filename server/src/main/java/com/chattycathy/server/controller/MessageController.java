package com.chattycathy.server.controller;

import com.chattycathy.server.model.Message;
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
public class MessageController {

    /**
     * @param message This is the message that is sent by a client and received on server-side
     * @return We return a message of our choice, since this is an app destination this will be sent to everyone
     * @see com.chattycathy.server.config.WebSocketConfig#configureMessageBroker(MessageBrokerRegistry)
     * WebSocketConfig.configureMessageBroker contains the config/routing for this endpoint
     */
    @MessageMapping("/main")
    @SendTo("/topic/main")
    public Message main(Message message) {
        return message;
    }

    /**
     * @param username This is the username of the client which is sent for the server to use in a message
     * @return We return a joining message which is sent to all connected users
     */
    @MessageMapping("/user-join")
    @SendTo("/topic/main")
    public Message userJoin(String username) {
        return new Message("Server", username + " joined the chat");
    }

    /**
     * @param username This is the username of the client which is sent for the server to use in a message
     * @return We return a leaving message which is sent to all users that are still connected
     */
    @MessageMapping("/user-leave")
    @SendTo("/topic/main")
    public Message userLeave(String username) {
        return new Message("Server", username + " left the chat");
    }
}
