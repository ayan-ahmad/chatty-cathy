package com.chattycathy.client.handler;

import com.chattycathy.client.ClientApplication;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class ServerInputHandler {

    private final ClientApplication clientApplication;
    private Scanner scanner;

    public ServerInputHandler(ClientApplication clientApplication) {
        this.clientApplication = clientApplication;
        this.scanner = new Scanner(System.in);
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    private String getConsoleInput() {
        log.info("Enter the WebSocket URL: ");
        String url = scanner.nextLine();
        if (url.isEmpty()) {
            url = "ws://localhost:8080/ws";
            log.warn("No URL provided, using default: {}", url);
        }
        return url;
    }

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
