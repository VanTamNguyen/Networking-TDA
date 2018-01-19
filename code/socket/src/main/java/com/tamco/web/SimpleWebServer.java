package com.tamco.web;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleWebServer {

    public static final int PORT = 8080;

    private ServerSocket serverSocket;

    private ExecutorService requestHandlingPool;

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
                clientSocket.getInputStream();
                clientSocket.getOutputStream();
            } catch (IOException e) {

            }
        };
        requestHandlingPool.submit(handlingTask);
    }

    public static void main(String[] args) {

    }
}
