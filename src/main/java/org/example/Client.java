package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final int port = 6666;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", port);
            Thread sendThread = new Thread(() -> sendMessages(socket));
            sendThread.start();

            Thread listenThread = new Thread(() -> listenMessages(socket));
            listenThread.start();

            sendThread.join();
            socket.close();
        } catch (ConnectException e) {
            System.out.println("Server not available.");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void sendMessages(Socket socket) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            while(true){
                Scanner scanner = new Scanner(System.in);
                String message = scanner.nextLine();
                String encryptedMessage = Cryptography.encrypter(message);

                if (message.equals("exit")) {
                    break;
                } else {
                    oos.writeObject(encryptedMessage);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void listenMessages(Socket socket) {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            while(true) {
                String encryptedMessage = (String) ois.readObject();
                String message = Cryptography.decoder(encryptedMessage);

                System.out.println("Received: " + message);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Incoming message could not be read.");
        } catch (IOException e) {
            System.out.println("Connection closed.");
        }
    }
}
