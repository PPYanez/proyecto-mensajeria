package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static final int port = 6666;
    // Array that holds the clients
    public static ArrayList<UserInfo> clients = new ArrayList<>();
    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket socket = serverSocket.accept();
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                if (clients.size() < 2) {
                    // Set isServerFull flag
                    oos.writeObject(false);
                    logger.info("Client connected.");
                    System.out.println("\u001B[31mClient connected.\u001B[0m");

                    UserInfo client = new UserInfo(socket, oos);
                    clients.add(client);

                    // Create a new thread for each client
                    Thread clientThread = new Thread(() -> ClientHandler.handleClient(client));
                    clientThread.start();
                } else {
                    // Set isServerFull flag
                    oos.writeObject(true);
                    logger.warn("Client trying to connect. Server is full.");
                    oos.close();
                    socket.close();
                }
            }
        }
    }
}