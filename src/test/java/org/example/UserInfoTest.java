package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class UserInfoTest {

    @Test
    void getUserSocket() {
        Socket socket = new Socket();
        UserInfo info = new UserInfo(socket, null);
        assertEquals(socket, info.getUserSocket());
    }

    @Test
    void closeSocket() {
        Socket socket = new Socket();
        UserInfo info = new UserInfo(socket, null);
        info.closeSocket();
        assertTrue(socket.isClosed());
        assertTrue(info.getUserSocket().isClosed());
    }

    @Test
    void getUserObjectOutputStream() throws IOException {
        Thread serverThread = new Thread(() -> Server.main(null));
        serverThread.start();

        Socket socket = new Socket("localhost", 6666);

        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

        UserInfo info = new UserInfo(socket, oos);
        assertEquals(oos, info.getUserObjectOutputStream());
    }
}