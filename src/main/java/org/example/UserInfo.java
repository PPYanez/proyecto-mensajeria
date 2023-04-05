package org.example;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class UserInfo {
    private final Socket userSocket;
    private final ObjectOutputStream oos;

    public UserInfo(Socket userSocket, ObjectOutputStream oos) {
        this.userSocket = userSocket;
        this.oos = oos;
    }

    public Socket getUserSocket() {
        return userSocket;
    }

    public ObjectOutputStream getUserObjectOutputStream() {
        return oos;
    }
}
