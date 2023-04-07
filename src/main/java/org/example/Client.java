package org.example;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final int port = 6666;

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", port)) {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            Scanner scanner = new Scanner(System.in);
            while (true) {
                String message = scanner.nextLine();
                if (message.equals("exit")) {
                    break;
                } else {
                    String encryptedMessage = Cryptography.encrypter(message);

                    oos.writeObject(encryptedMessage);
                }
            }
        } catch (ConnectException e) {
            System.out.println("Server not available.");
        }
    }
}
