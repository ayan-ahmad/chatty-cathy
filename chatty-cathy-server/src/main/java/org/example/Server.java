package org.example;

import java.net.*;
import java.io.*;

public class Server {
    private Socket s = null;
    private ServerSocket ss = null;
    private DataInputStream in = null;

    public Server(int port) {

        try
        {
            ss = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            s = ss.accept();
            System.out.println("Client accepted");

            in = new DataInputStream(
                    new BufferedInputStream(s.getInputStream()));

            String m = "";

            while (!m.equals("Over"))
            {
                try
                {
                    m = in.readUTF();
                    System.out.println(m);

                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection");

            s.close();
            in.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    public static void main(String args[])
    {
        Server s = new Server(5000);
    }
}

