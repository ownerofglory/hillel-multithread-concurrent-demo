package ua.ithillel.server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyThreadPoolServer implements AutoCloseable {
    private int port;
    private ServerSocket serverSocket;
    private boolean isOpen;
    private ExecutorService executorService;

    public MyThreadPoolServer(int port) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(port);
        this.isOpen = true;
        this.executorService = Executors.newFixedThreadPool(2);
    }

    public MyThreadPoolServer(int port, ExecutorService executorService) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(port);
        this.isOpen = true;
        this.executorService = executorService;
    }

    public void start() throws IOException {
        while (isOpen) {
            Socket socket = serverSocket.accept();

            Runnable reqRes = () -> {
                try (OutputStream outputStream = socket.getOutputStream()) {
                    PrintWriter printWriter
                            = new PrintWriter(new OutputStreamWriter(outputStream));

                    String response = "HTTP/1.1 200 OK\r\n";
                    response += "Content-Type: text/html\r\n";
                    response += "\r\n";
                    response += "<p>Hello</p>\r\n";
                    printWriter.write(response);
                    printWriter.flush();
                } catch (IOException e) {
                    System.out.println("Exception when getting socket output stream " + e.getMessage());
                }
            };

            executorService.submit(reqRes);
        }
    }

    @Override
    public void close() throws Exception {
        if (serverSocket != null) {
            isOpen = false;
            serverSocket.close();
        }
        executorService.shutdown();
    }
}
