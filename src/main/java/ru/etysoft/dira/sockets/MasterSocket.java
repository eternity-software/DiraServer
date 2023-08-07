package ru.etysoft.dira.sockets;

import com.google.gson.Gson;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import ru.etysoft.dira.requests.*;
import ru.etysoft.dira.requests.encryption.KeyRenewRequest;
import ru.etysoft.dira.requests.encryption.SendIntermediateKey;
import ru.etysoft.dira.requests.encryption.SubmitKeyRequest;
import ru.etysoft.dira.requests.handlers.*;
import ru.etysoft.dira.updates.ServerSyncUpdate;
import ru.etysoft.dira.updates.SubscribeUpdate;
import ru.etysoft.dira.utils.Logger;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MasterSocket extends WebSocketServer implements MasterSocketContract {

    public static final String TAG = "MasterDiraSocket";

    public static final List<String> SUPPORTED_APIS = Arrays.asList("0.0.3");

    private HashMap<String, ClientHandlerContract> clients = new HashMap<>();
    private HashMap<String, RoomUpdatesPool> updates = new HashMap<>();

    public MasterSocket(InetSocketAddress inetSocketAddress) {
        super(inetSocketAddress);

    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        Logger.info("New client connection from "  + webSocket.getRemoteSocketAddress(), TAG);
        ClientHandler clientHandler = new ClientHandler(this, webSocket);
        clients.put(webSocket.getRemoteSocketAddress().toString(), clientHandler);
        clientHandler.sendUpdate(new ServerSyncUpdate());
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        Logger.info("Closed connection from "  + webSocket.getRemoteSocketAddress(), TAG);
        unregisterClient(webSocket.getRemoteSocketAddress().toString());
    }

    @Override
    public void onMessage(WebSocket webSocket, String rawMessage) {
        Gson gson = new Gson();
        Request request = gson.fromJson(rawMessage, Request.class);
        System.out.println(rawMessage);

        if(request.getRequestType() == RequestType.SUBSCRIBE_REQUEST)
        {
            SubscribeRequest subscribeRequest = gson.fromJson(rawMessage, SubscribeRequest.class);

            for(String roomSecret : subscribeRequest.getRoomSecrets())
            {
                getUpdatesPool(roomSecret).registerClient(getClient(webSocket));
            }
            getClient(webSocket).sendUpdate(new SubscribeUpdate(0).setSubscribedRoomSecrets(subscribeRequest.getRoomSecrets()));
        }
        else if(request.getRequestType() == RequestType.VERIFY_ROOM_INFO)
        {
            VerifyRoomInfoRequest verifyRoomInfoRequest = gson.fromJson(rawMessage, VerifyRoomInfoRequest.class);
            new RoomInfoRequestHandler(verifyRoomInfoRequest, getClient(webSocket), this).process();
        }
        else if(request.getRequestType() == RequestType.SEND_MESSAGE_REQUEST)
        {
            SendMessageRequest sendMessageRequest = gson.fromJson(rawMessage, SendMessageRequest.class);
            new NewMessageHandler(sendMessageRequest, getClient(webSocket), this).process();
        }
        else if(request.getRequestType() == RequestType.GET_UPDATES)
        {
            GetUpdatesRequest getUpdatesRequest = gson.fromJson(rawMessage, GetUpdatesRequest.class);
            new GetUpdatesHandler(getUpdatesRequest, getClient(webSocket), this).process();
        }
        else if(request.getRequestType() == RequestType.UPDATE_ROOM)
        {
            RoomUpdateRequest roomUpdateRequest = gson.fromJson(rawMessage, RoomUpdateRequest.class);
            new RoomUpdateHandler(roomUpdateRequest, getClient(webSocket), this).process();
        }
        else if(request.getRequestType() == RequestType.UPDATE_MEMBER)
        {
            UpdateMemberRequest updateMemberRequest = gson.fromJson(rawMessage, UpdateMemberRequest.class);
            new MemberUpdateHandler(updateMemberRequest, getClient(webSocket), this).process();
        }
        else if(request.getRequestType() == RequestType.CREATE_INVITE)
        {
            CreateInviteRequest updateMemberRequest = gson.fromJson(rawMessage, CreateInviteRequest.class);
            new InviteHandler(updateMemberRequest, getClient(webSocket), this).process();
        }
        else if(request.getRequestType() == RequestType.ACCEPT_INVITE)
        {
            JoinRoomRequest joinRoomRequest = gson.fromJson(rawMessage, JoinRoomRequest.class);
            new InviteHandler(joinRoomRequest, getClient(webSocket), this).process();
        }
        else if(request.getRequestType() == RequestType.PING_MEMBERS)
        {
            PingMembersRequest pingMembersRequest = gson.fromJson(rawMessage, PingMembersRequest.class);
            new PingMembersHandler(pingMembersRequest, getClient(webSocket), this).process();
        }
        else if(request.getRequestType() == RequestType.PING_REACT)
        {
            PingReactRequest pingReactRequest = gson.fromJson(rawMessage, PingReactRequest.class);
            new PingReactHandler(pingReactRequest, getClient(webSocket), this).process();
        }
        else if(request.getRequestType() == RequestType.KEY_RENEW_REQUEST)
        {
            KeyRenewRequest keyRenewRequest = gson.fromJson(rawMessage, KeyRenewRequest.class);
            new KeyRenewHandler(keyRenewRequest, getClient(webSocket), this).process();
        }
        else if(request.getRequestType() == RequestType.SEND_INTERMEDIATE_KEY)
        {
            SendIntermediateKey sendIntermediateKey = gson.fromJson(rawMessage, SendIntermediateKey.class);
            new IntermediateKeyHandler(sendIntermediateKey, getClient(webSocket), this).process();
        }
        else if(request.getRequestType() == RequestType.SUBMIT_KEY)
        {
            SubmitKeyRequest submitKeyRequest = gson.fromJson(rawMessage, SubmitKeyRequest.class);
            new SubmitKeyHandler(submitKeyRequest, getClient(webSocket), this).process();
        }
        else if (request.getRequestType() == RequestType.MESSAGE_READ_REQUEST) {
            MessageReadRequest messageReadRequest = gson.fromJson(rawMessage, MessageReadRequest.class);
            new MessageReadHandler(messageReadRequest, getClient(webSocket), this).process();
        }
        else if (request.getRequestType() == RequestType.USER_STATUS_REQUEST) {
            SendUserStatusRequest userStatusRequest = gson.fromJson(rawMessage, SendUserStatusRequest.class);
            new UserStatusHandler(userStatusRequest, getClient(webSocket), this).process();
        }

    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {
        Logger.error("WebSocket error! Unregistering client", TAG);
        e.printStackTrace();

        unregisterClient(webSocket.getRemoteSocketAddress().toString());
        webSocket.close();
        Logger.info("Closed connection from "  + webSocket.getRemoteSocketAddress(), TAG);
    }

    public ClientHandlerContract getClient(WebSocket webSocket)
    {
        return clients.get(webSocket.getRemoteSocketAddress().toString());
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
    public RoomUpdatesPool getUpdatesPool(String roomSecret) {
        if(!updates.containsKey(roomSecret))
        {
            RoomUpdatesPool roomUpdatesPool = new RoomUpdatesPool(this);
            updates.put(roomSecret, roomUpdatesPool);
            return roomUpdatesPool;
        }
        return updates.get(roomSecret);
    }
}
