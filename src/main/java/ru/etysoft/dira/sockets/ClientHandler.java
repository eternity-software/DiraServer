package ru.etysoft.dira.sockets;

import com.google.gson.Gson;
import org.java_websocket.WebSocket;
import ru.etysoft.dira.requests.Request;
import ru.etysoft.dira.updates.Update;

public class ClientHandler implements ClientHandlerContract {


    private final MasterSocketContract masterSocketContract;
    private final WebSocket clientConnection;

    public ClientHandler(MasterSocketContract masterSocketContract,
                         WebSocket clientConnection) {

        this.masterSocketContract = masterSocketContract;
        this.clientConnection = clientConnection;
    }

    @Override
    public void onRequest(Request request) {

    }

    @Override
    public void sendUpdate(Update update) {
        Gson gson = new Gson();
        if(!clientConnection.isOpen()) return;
        clientConnection.send(gson.toJson(update));
    }

    @Override
    public String getAddress() {
        return clientConnection.getRemoteSocketAddress().toString();
    }

    @Override
    public boolean isOpen() {
        return clientConnection.isOpen();
    }


}
