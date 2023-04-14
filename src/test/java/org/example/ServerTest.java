package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ServerTest {
    /**
     * Test if the server receives connections correctly
     */
    @Test
    public void main() {
        // start the server
        Thread serverThread = new Thread(() -> Server.main(null));
        serverThread.start();

        // start the client
        Thread clientThread = new Thread(() -> Client.main(null));
        clientThread.start();

        // close client
        clientThread.interrupt();

        // close server
        serverThread.interrupt();

        assertAll(() -> {
            assertTrue(serverThread.isInterrupted());
            assertTrue(clientThread.isInterrupted());
        });
    }
}