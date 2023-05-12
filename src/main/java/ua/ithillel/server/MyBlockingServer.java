package ua.ithillel.server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyBlockingServer implements AutoCloseable {
    private int port;
    private ServerSocket serverSocket;
    private boolean isOpen;

    public MyBlockingServer(int port) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(port);
        this.isOpen = true;
    }

    public void start() throws IOException {
        while (isOpen) {
            Socket socket = serverSocket.accept();

            try (OutputStream outputStream = socket.getOutputStream()) {
                PrintWriter printWriter
                        = new PrintWriter(new OutputStreamWriter(outputStream));

                String response = "HTTP/1.1 200 OK\r\n";
                response += "Content-Type: text/html\r\n";
                response += "\r\n";
                response += "<p>Hello</p>\r\n";
                printWriter.write(response);
                printWriter.flush();
            }
        }
    }

    @Override
    public void close() throws Exception {
        if (serverSocket != null) {
            isOpen = false;
            serverSocket.close();
        }
    }
}
