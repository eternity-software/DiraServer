package ru.etysoft.dira;

import ru.etysoft.dira.sockets.MasterSocket;
import java.net.InetSocketAddress;

public class DiraServer {

    public static long timeSeverStartup;
    public static void main(String[] args) {
        timeSeverStartup = System.currentTimeMillis();
        startMasterSocket();
    }

    private static void startMasterSocket()
    {
        String host = "164.132.138.80";
        int port = 8888;

        MasterSocket server = new MasterSocket(new InetSocketAddress(host, port));
        server.run();
    }

}