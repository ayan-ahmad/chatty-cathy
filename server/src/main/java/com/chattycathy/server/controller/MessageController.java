package com.chattycathy.server.controller;

import com.chattycathy.server.model.Model;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class MessageController {

    @MessageMapping("/ping")
    @SendTo("/topic/ping")
    public Model ping(Model message) {
        log.info("<{}>: {}", message.getName(), message.getMessage());
        return new Model("Server", "Welcome to Chatty Cathy!");
    }
}
