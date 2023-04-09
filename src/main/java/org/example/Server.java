package org.example;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static final int port = 6666;
    // Array that holds the clients
    public static ArrayList<UserInfo> clients = new ArrayList<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket socket = serverSocket.accept();
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                // Check length of clients arraylist
                if (clients.size() < 2) {
                    UserInfo client = new UserInfo(socket, oos);
                    clients.add(client);

                    // Create a new thread for each client
                    Thread clientThread = new Thread(() -> ClientHandler.handleClient(client));
                    clientThread.start();
                } else {
                    oos.writeObject("Server is full, try again later.");
                    oos.close();
                    socket.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}