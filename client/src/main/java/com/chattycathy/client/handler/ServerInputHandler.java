package com.chattycathy.client.handler;

import com.chattycathy.client.ClientApplication;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * Handles the input from the user to connect to a server that they can specify or else connect to a default server
 */
@Slf4j
public class ServerInputHandler {

    private final ClientApplication clientApplication;
    private Scanner scanner;
    private static final String DEFAULT_URL = "ws://localhost:8080/ws";

    public ServerInputHandler(ClientApplication clientApplication) {
        this.clientApplication = clientApplication;
        this.scanner = clientApplication.getScanner();
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    private String getConsoleInput() {
        log.info("Enter the WebSocket URL: ");
        String url = scanner.nextLine();
        if (url.isEmpty()) {
            url = DEFAULT_URL;
            log.warn("No URL provided, using default: {}", DEFAULT_URL);
        }
        return url;
    }

    /**
     * Connects to the server using the WebSocket URL provided by the user
     * @exception NullPointerException if the URL provided is invalid
     * Exceptions are also handled if the stomp client returns a throwable error
     */
    public void connectToServer() {

        String url = getConsoleInput();

        try {
            clientApplication.getStompClient().connectAsync(url, clientApplication.getSessionHandler())
                    .whenComplete((session, throwable) -> {
                        if (throwable != null) {
                            log.error("WebSocket connection failed: {}", throwable.getMessage());
                        }
                    });
        } catch (NullPointerException e) {
            log.error("Null pointer exception caused by invalid URL: {}", url);
        } catch (Exception e) {
            log.error("Unexpected error during WebSocket connection: {}", e.getMessage());
        }

    }

}
