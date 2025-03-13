package com.chattycathy.client.config;

import com.chattycathy.client.handler.ClientStompSessionHandler;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class with configurations for the websocket connection
 */
@Configuration
public class WebSocketClientConfig {

    /**
     * Initializes and returns our WebSocket Stomp client
     * This is used during the initialization of the connection to link the configurations.
     * @return WebSocket Stomp Client with our custom configuration
     */
    @Bean
    public WebSocketStompClient webSocketStompClient() {
        List<Transport> transports = new ArrayList<>();
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        SockJsClient sockJsClient = new SockJsClient(transports);

        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
        stompClient.setMessageConverter(mappingJackson2MessageConverter());

        return stompClient;
    }

    /**
     * Initializes and returns a customized configuration for MappingJackson2MessageConverter.
     * This is used to encode and decode messages from and to the websocket.
     * @return Customized configuration of the Jackson2MessageConverter
     */
    @Bean
    public MappingJackson2MessageConverter mappingJackson2MessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return new MappingJackson2MessageConverter(objectMapper);
    }


    /**
     * @return A newly created instance of the ClientStompSessionHandler
     */
    @Bean
    public StompSessionHandler stompSessionHandler() {
        return new ClientStompSessionHandler(consoleInputScanner());
    }

    /**
     * @return reusable instance of scanner which reads from console.
     */
    @Bean
    public Scanner consoleInputScanner() {
        return new Scanner(System.in);
    }
}