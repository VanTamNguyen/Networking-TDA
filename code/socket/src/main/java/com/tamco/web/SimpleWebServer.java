package com.tamco.web;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleWebServer {

    public static final int PORT = 54000;

    private ServerSocket serverSocket;

    private ExecutorService requestHandlingPool;

    private SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:Ss z");

    public SimpleWebServer() { }

    public void init() throws IOException {
        serverSocket = new ServerSocket(PORT);
        requestHandlingPool = Executors.newFixedThreadPool(20);
    }

    public void startServing() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                handleRequest(clientSocket);

            } catch (IOException e) {
                System.out.println("Error while handshaking: " + e.getMessage());
            }
        }
    }

    private void handleRequest(Socket clientSocket) {
        Runnable handlingTask = () -> {
            try {
                InputStream inputStream = clientSocket.getInputStream();
                OutputStream outputStream = clientSocket.getOutputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String line; StringBuilder builder = new StringBuilder("<html><body><h1>Welcome to TamCO Web Server</h1><p>");
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                    builder.append(line).append("<br/>");
                    if (line.isEmpty()) break;
                }
                builder.append("</p></body></html>");

                String body = builder.toString();
                String res = "HTTP/1.1 200 OK\n"
                + "Server: TamCO/0.1\r\n"
                + "Date: " + format.format(new java.util.Date()) + "\n"
                + "Content-Type: text/html; charset=UTF-8\n"
                + "Connection: close\n"
                + "Content-Length: " + body.getBytes().length + "\n\n"
                + body;

                outputStream.write(res.getBytes());
                outputStream.flush();
                outputStream.close();
                inputStream.close();
                bufferedReader.close();

                clientSocket.close();

            } catch (IOException e) {
                System.out.println("Error while handling HTTP Request: " + e.getMessage());
            }
        };
        requestHandlingPool.submit(handlingTask);
    }

    public static void main(String[] args) {
        SimpleWebServer webServer = new SimpleWebServer();
        try {
            webServer.init();
            webServer.startServing();
        } catch (IOException e) {
            System.out.println("Error occurred while starting web server: " + e.getMessage());
        }
    }
}
