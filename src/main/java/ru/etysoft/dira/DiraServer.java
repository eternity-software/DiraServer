package ru.etysoft.dira;

import ru.etysoft.dira.sockets.MasterSocket;

import java.net.InetSocketAddress;

public class DiraServer {
    public static void main(String[] args) {
        startMasterSocket();
    }

    private static void startMasterSocket()
    {
        String host = "localhost";
        int port = 8888;

        MasterSocket server = new MasterSocket(new InetSocketAddress(host, port));
        server.run();
    }

}