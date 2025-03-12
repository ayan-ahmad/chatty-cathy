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

    private void testConnectToServer(String inputUrl, String expectedUrl) {
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextLine()).thenReturn(inputUrl);

        serverInputHandler.setScanner(scanner);
        serverInputHandler.connectToServer();

        verify(stompClient, times(1)).connectAsync(eq(expectedUrl), any());
    }

    @Test
    void testConnectToServerWithValidUrl() {
        testConnectToServer("ws://localhost:8080/ws", "ws://localhost:8080/ws");
    }

    @Test
    void testConnectToServerWithEmptyUrl() {
        testConnectToServer("", "ws://localhost:8080/ws");
    }

    @Test
    void testConnectToServerWithInvalidUrl() {
        testConnectToServer("invalid-url", "invalid-url");
    }
}
