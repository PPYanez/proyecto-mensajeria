package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler {
    public static void handleClient(UserInfo currentClient) {
        try{
            Socket socket = currentClient.getUserSocket();

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            while(true){
                String message = (String) ois.readObject();
                if (message.equals("exit")) {
                    break;
                } else {
                    System.out.println("Received message: " + message);

                    for(UserInfo client : Server.clients){
                        if(socket != client.getUserSocket()){
                            ObjectOutputStream clientOos = client.getUserObjectOutputStream();
                            clientOos.writeObject(message);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
