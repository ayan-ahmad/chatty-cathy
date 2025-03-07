package org.example;

import java.net.*;
import java.io.*;

public class Server {

    private ServerSocket ss = null;

    public Server(int port) {
        try {
            ss = new ServerSocket(port);
            System.out.println("Server started");

            while (true) {
                System.out.println("Waiting for a client ...");
                Socket clientSocket = ss.accept();
                System.out.println("Client accepted");

                ClientHandler clientHandler = new ClientHandler(clientSocket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        Server s = new Server(5000);
    }
}