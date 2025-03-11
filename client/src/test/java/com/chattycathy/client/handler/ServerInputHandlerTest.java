package com.chattycathy.client.handler;

import com.chattycathy.client.ClientApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.Scanner;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServerInputHandlerTest {

    private WebSocketStompClient stompClient;
    private ServerInputHandler serverInputHandler;

    @BeforeEach
    void setUp() {
        stompClient = mock(WebSocketStompClient.class);
        ClientApplication clientApplication = mock(ClientApplication.class);
        when(clientApplication.getStompClient()).thenReturn(stompClient);
        serverInputHandler = new ServerInputHandler(clientApplication);
    }

    @Test
    void testConnectToServerWithValidUrl() {
        String validUrl = "ws://localhost:8080/ws";
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextLine()).thenReturn(validUrl);

        serverInputHandler.setScanner(scanner);
        serverInputHandler.connectToServer();

        verify(stompClient, times(1)).connectAsync(eq(validUrl), any());
    }

    @Test
    void testConnectToServerWithEmptyUrl() {
        String defaultUrl = "ws://localhost:8080/ws";
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextLine()).thenReturn("");

        serverInputHandler.setScanner(scanner);
        serverInputHandler.connectToServer();

        verify(stompClient, times(1)).connectAsync(eq(defaultUrl), any());
    }

    @Test
    void testConnectToServerWithInvalidUrl() {
        String invalidUrl = "invalid-url";
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextLine()).thenReturn(invalidUrl);

        serverInputHandler.setScanner(scanner);
        serverInputHandler.connectToServer();

        verify(stompClient, times(1)).connectAsync(eq(invalidUrl), any());
    }
}
