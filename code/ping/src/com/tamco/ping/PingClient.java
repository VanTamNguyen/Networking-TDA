package com.tamco.ping;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by TamCO on 1/22/18.
 */
public class PingClient {

    public PingClient() {}

    public void ping(InetAddress serverAddress, int serverPort) {
        long start = 0, end = 0;

        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            System.out.println("Error occurred while creating socket: " + e.getMessage());
            return;
        }

        DatagramPacket ping = new DatagramPacket("ping".getBytes(), "ping".getBytes().length, serverAddress, serverPort);
        try {
            start = System.currentTimeMillis();
            socket.send(ping);
        } catch (IOException e) {
            System.out.println("Error occurred while ping: " + e.getMessage());
            socket.close();
            return;
        }

        DatagramPacket receivedPacket = new DatagramPacket(new byte[100], 100);
        try {
            socket.receive(receivedPacket);
            end = System.currentTimeMillis();
            socket.close();

            int length = receivedPacket.getLength();
            byte[] data = Arrays.copyOf(receivedPacket.getData(), length);
            String receivedMessage = new String(data);
            System.out.println("Ping response [" + receivedMessage + "] from server in " + (end - start) + " milliseconds");
        } catch (IOException e) {
            System.out.println("Cannot receive ping response: " + e.getMessage());
        }
    }

    public void schedulePing() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    ping(InetAddress.getByName("localhost"), PingServer.PORT);
                } catch (UnknownHostException e) {
                    System.out.println("Cannot ping to localhost because of " + e.getMessage());
                }
            }
        }, 1000, 1000);
    }

    public static void main(String[] args) {
        new PingClient().schedulePing();
    }
}
