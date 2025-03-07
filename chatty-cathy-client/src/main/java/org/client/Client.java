package org.client;

import org.common.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;

public class Client {

    private static final Logger logger = LoggerFactory.getLogger(Client.class);
    private ObjectInputStream serverIn = null;

    public Client(String addr, int port) throws IOException {
        BufferedReader in;
        ObjectOutputStream out;

        Socket s;
        try {
            s = new Socket(addr, port);
            logger.info("Connected");
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new ObjectOutputStream(s.getOutputStream());
            serverIn = new ObjectInputStream(s.getInputStream());

            Thread readThread = new Thread(() -> {
                try {
                    while (true) {
                        Message message = (Message) serverIn.readObject();
                        logger.info(message.getUsername() + ": " + message.getMessage());
                    }
                } catch (IOException | ClassNotFoundException e) {
                    logger.error(String.valueOf(e));
                }
            });
            readThread.start();
        } catch (IOException i) {
            logger.error(String.valueOf(i));
            return;
        }

        String m = "";

        logger.info("Please enter your username: ");
        String username = in.readLine();

        while (!m.equals("Over")) {
            try {
                m = in.readLine();
                Message message = new Message(m, username);
                out.writeObject(message);
            } catch (IOException i) {
                logger.error(String.valueOf(i));
            }
        }

        try {
            in.close();
            out.close();
            s.close();
        } catch (IOException i) {
            logger.error(String.valueOf(i));
        }
    }

    public static void main(String[] args) throws IOException {
        new Client("127.0.0.1", 5000);
    }
}