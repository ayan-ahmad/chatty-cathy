package com.chattycathy.client;

import com.chattycathy.client.handler.ClientStompSessionHandler;
import com.chattycathy.client.handler.ServerInputHandler;
import com.chattycathy.client.model.User;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.Scanner;

@SpringBootApplication
@Slf4j
@Getter
public class ClientApplication implements CommandLineRunner {

	private final WebSocketStompClient stompClient;

	private final ClientStompSessionHandler sessionHandler;

	private final Scanner scanner;

	public ClientApplication(WebSocketStompClient stompClient, ClientStompSessionHandler sessionHandler, Scanner scanner) {
		this.stompClient = stompClient;
		this.sessionHandler = sessionHandler;
		this.scanner = scanner;
	}

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	/**
	 * Gets username and starts server
	 * @param args incoming main method arguments
	 */
	@Override
	public void run(String... args) {
		sessionHandler.setUser(new User(scanner));
		new ServerInputHandler(this).connectToServer();
	}
}
