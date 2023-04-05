package org.example;

import java.io.ObjectOutputStream;
import java.net.Socket;

public record UserInfo(Socket userSocket, ObjectOutputStream oos) {
}
