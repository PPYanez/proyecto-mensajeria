package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int port = 6666;
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while(true){
                Socket socket = serverSocket.accept();

                // Create a new thread for each client
                Thread clientThread = new Thread(() -> ClientHandler.handleClient(socket));
                clientThread.start();
            }
        }
    }
}