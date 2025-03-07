package org.client;

import org.common.Message;

import java.io.*;
import java.net.*;

public class Client {

    private ObjectInputStream serverIn = null;

    public Client(String addr, int port) throws IOException {
        BufferedReader in;
        ObjectOutputStream out;

        Socket s;
        try {
            s = new Socket(addr, port);
            System.out.println("Connected");
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new ObjectOutputStream(s.getOutputStream());
            serverIn = new ObjectInputStream(s.getInputStream());

            Thread readThread = new Thread(() -> {
                try {
                    while (true) {
                        Message message = (Message) serverIn.readObject();
                        System.out.println(message.getUsername() + ": " + message.getMessage());
                    }
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println(e);
                }
            });
            readThread.start();
        } catch (IOException i) {
            System.out.println(i);
            return;
        }

        String m = "";

        System.out.println("Please enter your username: ");
        String username = in.readLine();

        while (!m.equals("Over")) {
            try {
                m = in.readLine();
                Message message = new Message(m, username);
                out.writeObject(message);
            } catch (IOException i) {
                System.out.println(i);
            }
        }

        try {
            in.close();
            out.close();
            s.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) throws IOException {
        new Client("127.0.0.1", 5000);
    }
}