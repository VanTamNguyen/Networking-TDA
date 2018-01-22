package com.tamco.ping;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by TamCO on 1/22/18.
 */

public class PingServer {

    public static final int PORT = 42000;

    public PingServer() {
    }

    public void startServing() throws SocketException {
        byte[] buffer = new byte[100];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        DatagramSocket socket = new DatagramSocket(PORT);

        while (true) {
            try {
                socket.receive(packet);
                String receivedMessage = new String(packet.getData());

                if (receivedMessage.startsWith("ping")) {
                    InetAddress senderAddress = packet.getAddress();
                    int senderPort = packet.getPort();
                    DatagramPacket pong = new DatagramPacket("pong".getBytes(), "pong".getBytes().length, senderAddress, senderPort);
                    socket.send(pong);
                }

            } catch (IOException e) {
                System.out.println("Error while receiving ping packet: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        try {
            new PingServer().startServing();
        } catch (SocketException e) {
            System.out.println("Error occurred while starting Ping Server: " + e.getMessage());
        }
    }
}
