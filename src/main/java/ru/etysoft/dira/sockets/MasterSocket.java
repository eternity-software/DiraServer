package ru.etysoft.dira.sockets;

import com.google.gson.Gson;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import ru.etysoft.dira.requests.Request;
import ru.etysoft.dira.requests.RequestType;
import ru.etysoft.dira.requests.SubscribeRequest;
import ru.etysoft.dira.updates.SubscribeUpdate;
import ru.etysoft.dira.utils.Logger;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MasterSocket extends WebSocketServer implements MasterSocketContract {

    public static final String TAG = "MasterDiraSocket";

    public static final List<String> SUPPORTED_APIS = Arrays.asList("0.0.1");

    private HashMap<String, ClientHandlerContract> clients = new HashMap<>();
    private HashMap<String, UpdatesPool> updates = new HashMap<>();

    public MasterSocket(InetSocketAddress inetSocketAddress) {
        super(inetSocketAddress);
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        Logger.info("New client connection from "  + webSocket.getRemoteSocketAddress(), TAG);
        ClientHandler clientHandler = new ClientHandler(this, webSocket);
        clients.put(webSocket.getRemoteSocketAddress().toString(), clientHandler);
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        unregisterClient(webSocket.getRemoteSocketAddress().toString());
        Logger.info("Closed connection from "  + webSocket.getRemoteSocketAddress(), TAG);
    }

    @Override
    public void onMessage(WebSocket webSocket, String rawMessage) {
        Gson gson = new Gson();
        Request request = gson.fromJson(rawMessage, Request.class);

        if(request.getRequestType() == RequestType.SUBSCRIBE_REQUEST)
        {
            SubscribeRequest subscribeRequest = gson.fromJson(rawMessage, SubscribeRequest.class);

            for(String roomSecret : subscribeRequest.getRoomSecrets())
            {
                getUpdatesPool(roomSecret).registerClient(getClient(webSocket));
            }
            getClient(webSocket).sendUpdate(new SubscribeUpdate(0).setSubscribedRoomSecrets(subscribeRequest.getRoomSecrets()));
        }
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {
        Logger.error("WebSocket error! Unregistering client", TAG);
        e.printStackTrace();
        unregisterClient(webSocket.getRemoteSocketAddress().toString());
        Logger.info("Closed connection from "  + webSocket.getRemoteSocketAddress(), TAG);
    }

    public ClientHandlerContract getClient(WebSocket webSocket)
    {
        return clients.get(webSocket.getRemoteSocketAddress());
    }

    public void registerClient(String remoteAddress, ClientHandler clientHandler)
    {
        if(!clients.containsKey(remoteAddress))
        {
            clients.put(remoteAddress, clientHandler);
        }
    }

    public void unregisterClient(String remoteAddress)
    {
        clients.remove(remoteAddress);
    }

    @Override
    public void onStart() {
        Logger.info("MasterSocket Started", TAG);
        for(String apiVersion : SUPPORTED_APIS)
        {
            Logger.info("Version " + apiVersion + " supported", TAG);
        }

    }



    @Override
    public void registerClient(ClientHandler clientHandler) {
        registerClient(clientHandler.getAddress(), clientHandler);
    }

    @Override
    public void unregisterClient(ClientHandler clientHandler) {
        unregisterClient(clientHandler.getAddress());
    }

    @Override
    public boolean hasClient(String address) {
        return clients.containsKey(address);
    }

    @Override
    public UpdatesPool getUpdatesPool(String roomSecret) {
        if(!updates.containsKey(roomSecret))
        {
            UpdatesPool updatesPool = new UpdatesPool(this);
            updates.put(roomSecret, updatesPool);
            return updatesPool;
        }
        return updates.get(roomSecret);
    }
}
