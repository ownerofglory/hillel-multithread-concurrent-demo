package ua.ithillel.server;

import ua.ithillel.weather.client.WeatherClient;
import ua.ithillel.weather.model.WeatherInfo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyWeatherServer implements AutoCloseable {
    private int port;
    private ServerSocket serverSocket;
    private boolean isOpen;
    private final ExecutorService executorService;
    private final WeatherClient client;

    public MyWeatherServer(int port, WeatherClient client) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(port);
        this.isOpen = true;
        this.executorService = Executors.newFixedThreadPool(2);
        this.client = client;
    }

    public MyWeatherServer(int port, ExecutorService executorService, WeatherClient client) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(port);
        this.isOpen = true;
        this.executorService = executorService;
        this.client = client;
    }

    public void start() throws IOException {
        while (isOpen) {
            Socket socket = serverSocket.accept();

            Runnable reqRes = () -> {
                serverRequest(socket);
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

    public void serverRequest(Socket socket) {
        try (socket;
                OutputStream outputStream = socket.getOutputStream();
             PrintWriter printWriter
                     = new PrintWriter(new OutputStreamWriter(outputStream));
             InputStream inputStream = socket.getInputStream();

             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        ) {

                 // GET /?city=Odessa
            String requestLine = br.readLine();
            String[] parts = requestLine.split(" ");
            // /?city=Odessa
            String query = parts[1];
            String[] valParams = query.split("=");
            // Odessa
            String city = valParams[1];

            WeatherInfo weatherByCity = client.getWeatherByCity(city);


            String response = "HTTP/1.1 200 OK\r\n";
            response += "Content-Type: text/html\r\n";
            response += "\r\n";
            response += "<p>Hello</p>\r\n";
            response += weatherByCity;
            printWriter.write(response);
            printWriter.flush();
        } catch (IOException e) {
            System.out.println("Exception when getting socket output stream " + e.getMessage());
        }
    }
}
