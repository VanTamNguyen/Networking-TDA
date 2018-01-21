package com.tamco.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by TamCO on 21/01/2018.
 */
public class Receiver {

    public static final int PORT = 51000;

    public Receiver() {}

    public void receive() {
        try {
            byte[] buffer = new byte[100];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            DatagramSocket socket = new DatagramSocket(PORT);

            while (true) {
                socket.receive(packet);
                String receivedMessage = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received message [" + receivedMessage + "] from " + packet.getAddress() + ":" + packet.getPort());
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Receiver().receive();
    }
}
