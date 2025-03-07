package org.server;

import org.common.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(ClientHandler.class);
    private final Socket clientSocket;
    private ObjectOutputStream out;
    private final Server server;

    public ClientHandler(Socket socket, Server server) {
        this.clientSocket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        ObjectInputStream in;
        try {
            in = new ObjectInputStream(new BufferedInputStream(clientSocket.getInputStream()));
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            Message message;

            while (true) {
                try {
                    message = (Message) in.readObject();
                    if (message.getMessage().equals("QUIT")) break;
                    logger.info(message.getUsername() + ": " + message.getMessage());
                    server.broadcast(message);
                } catch (IOException | ClassNotFoundException e) {
                    logger.error(String.valueOf(e));
                }
            }

            in.close();
            clientSocket.close();
        } catch (IOException e) {
            logger.error(String.valueOf(e));
        }
    }

    /**
     * Sends a message to the client.
     *
     * @param message the message to send
     */
    public void sendMessage(Message message) {
        try {
            out.writeObject(message);
        } catch (IOException e) {
            logger.error(String.valueOf(e));
        }
    }
}
