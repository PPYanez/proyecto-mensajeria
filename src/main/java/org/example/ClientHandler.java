package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientHandler {
    public static void handleClient(Socket socket) {
        try{
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            while(true){
                String message = (String) ois.readObject();
                if (message.equals("exit")) {
                    break;
                } else {
                    System.out.println("Received message: " + message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
