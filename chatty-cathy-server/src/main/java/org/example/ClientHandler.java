package org.example;

import org.example.Message;

import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private ObjectInputStream in;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            in = new ObjectInputStream(new BufferedInputStream(clientSocket.getInputStream()));
            Message message;

            while (true) {
                try {
                    message = (Message) in.readObject();
                    if (message.getMessage().equals("Over")) break;
                    System.out.println(message.getUsername() + ": " + message.getMessage());
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
}