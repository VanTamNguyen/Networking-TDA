package com.tamco.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by TamCO on 21/01/2018.
 */
public class SimpleWebClient {

    public SimpleWebClient() {}

    public String get(String host, int port, String resource) throws IOException {
        Socket socket = new Socket(host, port);
        OutputStream out = socket.getOutputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String get = "GET " + resource + " HTTP/1.1\n\n";
        out.write(get.getBytes());
        out.flush();

        String line; StringBuilder builder = new StringBuilder();

        do {
            line = in.readLine();
            builder.append(line).append("\n");
        } while (!line.contains("</html>") && !line.contains("</HTML>"));

        out.close();
        in.close();
        socket.close();

        return builder.toString();
    }

    public static void main(String[] args) {
        String response = null;

        try {
            response = new SimpleWebClient().get("localhost", 54000, "/");
            System.out.println(response);
        } catch (IOException e) {
            System.out.println("Error occured while getting http://localhost:54000 [" + e.getMessage() + "]");
        }

        try {
            response = new SimpleWebClient().get("google.com", 80, "/");
            System.out.println("\n\n\n\n" + response);
        } catch (IOException e) {
            System.out.println("\n\n\n\nError occured while getting http://google.com [" + e.getMessage() + "]");
        }
    }
}
