package org.server;

import org.common.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {

    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    private final List<ClientHandler> clients = Collections.synchronizedList(new ArrayList<>());

    public Server(int port) {
        ServerSocket ss;
        try {
            ss = new ServerSocket(port);
            logger.info("Server started");
            logger.info("Waiting for clients");

            int connections = 0;
            while (connections < 50) {
                Socket clientSocket = ss.accept();
                logger.info("New client accepted");

                ClientHandler clientHandler = new ClientHandler(clientSocket, this);
                clients.add(clientHandler);
                Thread thread = new Thread(clientHandler);
                thread.start();

                connections++;
            }

        } catch (IOException e) {
            logger.error(String.valueOf(e));
        }
    }

    /**
     * Broadcasts a message to all clients.
     *
     * @param message the message to broadcast
     */
    public void broadcast(Message message) {
        synchronized (clients) {
            for (ClientHandler client : clients) {
                client.sendMessage(message);
            }
        }
    }

    public static void main(String[] args) {
        new Server(5000);
    }
}