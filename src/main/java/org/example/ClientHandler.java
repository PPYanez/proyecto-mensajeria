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
                String encryptedMessage = (String) ois.readObject();
                String message = Cryptography.decoder(encryptedMessage);

                if (message.equals("exit")) {
                    break;
                } else {
                    System.out.println("Received: " + message);

                    for(UserInfo client : Server.clients){
                        if(socket != client.getUserSocket()){
                            ObjectOutputStream clientOos = client.getUserObjectOutputStream();
                            clientOos.writeObject(encryptedMessage);
                        }
                    }
                }
            }

            // Cleanup
            ois.close();
            socket.close();
            Server.clients.remove(currentClient);
        } catch (IOException e) {
            currentClient.closeSocket();
            Server.clients.remove(currentClient);
            System.out.println("Client disconnected.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
