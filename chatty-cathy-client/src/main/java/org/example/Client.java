package org.example;

import java.io.*;
import java.net.*;

public class Client {
    private Socket s = null;
    private DataInputStream in = null;
    private ObjectOutputStream out = null;


    public Client(String addr, int port)
    {
        try {
            s = new Socket(addr, port);
            System.out.println("Connected");

            in = new DataInputStream(System.in);

            out = new ObjectOutputStream(s.getOutputStream());
        }
        catch (UnknownHostException u) {
            System.out.println(u);
            return;
        }
        catch (IOException i) {
            System.out.println(i);
            return;
        }

        String m = "";

        while (!m.equals("Over")) {
            try {
                m = in.readLine();
                Message message = new Message(m, "Test Username");
                out.writeObject(message);
            }
            catch (IOException i) {
                System.out.println(i);
            }
        }

        try {
            in.close();
            out.close();
            s.close();
        }
        catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        Client c = new Client("127.0.0.1", 5000);
    }
}
