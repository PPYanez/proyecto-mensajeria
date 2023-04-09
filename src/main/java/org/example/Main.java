package org.example;

import java.io.IOException;
import java.net.*;

public class Main {
    public static void main(String[] args) {
        boolean serverUp = checkServerStatus();
    }

    /**
     * Apache's camel implementation to check if a server is up or not
     * in the local network
     **/
    private static boolean checkServerStatus()
    {
        boolean serverUp = true;

        ServerSocket ss = null;
        try {
            ss = new ServerSocket(6666);
            serverUp = false;
        } catch (IOException e) {
        } finally {
            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                    // should not be thrown
                }
            }
        }

        return serverUp;
    }
}