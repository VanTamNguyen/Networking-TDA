package com.tamco.udp;

import java.io.IOException;
import java.net.*;
import java.util.*;

/**
 * Created by TamCO on 21/01/2018.
 */
public class Broadcaster {

    public Broadcaster() {}

    public void broadcast() throws SocketException{
        final String broadcastMessage = "Hello from Broadcaster!";
        final List<InetAddress> broadcastAddresses = new ArrayList<>();
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface networkInterface = interfaces.nextElement();
            if (networkInterface.isLoopback()) {
                continue;
            }

            for (InterfaceAddress address : networkInterface.getInterfaceAddresses()) {
                if (address.getBroadcast() != null) {
                    broadcastAddresses.add(address.getBroadcast());
                }
            }
        }

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (InetAddress broadcastAddress : broadcastAddresses) {
                    sendUdpPacket(broadcastMessage, broadcastAddress, Receiver.PORT);
                }
            }
        }, 2000, 2000);
    }

    private void sendUdpPacket(String message, InetAddress address, int port) {
        try {
            DatagramSocket socket = new DatagramSocket();
            DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, address, port);
            socket.send(packet);
            System.out.println("Broadcast \"" + message + "\" to " + address.toString());
            socket.close();

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Broadcaster broadcaster = new Broadcaster();
        try {
            broadcaster.broadcast();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
