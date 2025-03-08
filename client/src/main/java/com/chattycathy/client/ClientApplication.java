package com.chattycathy.client;

import com.chattycathy.client.handler.ClientStompSessionHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.messaging.WebSocketStompClient;

@SpringBootApplication
public class ClientApplication implements CommandLineRunner {

	private final WebSocketStompClient stompClient;

	private final ClientStompSessionHandler sessionHandler;

	public ClientApplication(WebSocketStompClient stompClient, ClientStompSessionHandler sessionHandler) {
		this.stompClient = stompClient;
		this.sessionHandler = sessionHandler;
	}

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@Override
	public void run(String... args) {
		stompClient.connectAsync("ws://localhost:8080/ws", sessionHandler);
	}
}