package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientHandler {
    public static void handleClient(Socket socket) {
        while (true) {
            try {
                // Listen for messages from the client
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

                String message = (String) ois.readObject();

                if (message.equals("exit")) {
                    break;
                } else {
                    System.out.println(message);
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
