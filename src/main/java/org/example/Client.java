package org.example;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

public class Client {
    private static final int port = 6666;

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", port)) {
            System.out.println("Connected to server.");
        } catch (ConnectException e) {
            System.out.println("Server not available.");
        }
    }
}
