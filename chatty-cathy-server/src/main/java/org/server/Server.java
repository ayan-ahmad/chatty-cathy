package org.server;

import org.common.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {

    private final List<ClientHandler> clients = Collections.synchronizedList(new ArrayList<>());

    public Server(int port) {
        ServerSocket ss;
        try {
            ss = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting for clients");

            int connections = 0;
            while (connections < 50) {
                Socket clientSocket = ss.accept();
                System.out.println("New client accepted");

                ClientHandler clientHandler = new ClientHandler(clientSocket, this);
                clients.add(clientHandler);
                Thread thread = new Thread(clientHandler);
                thread.start();

                connections++;
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }

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