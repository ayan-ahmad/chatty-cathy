package org.server;

import org.common.Message;

import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
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
                    System.out.println(message.getUsername() + ": " + message.getMessage());
                    server.broadcast(message);
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println(e);
                }
            }

            in.close();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void sendMessage(Message message) {
        try {
            out.writeObject(message);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
