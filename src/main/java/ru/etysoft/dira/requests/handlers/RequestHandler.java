package ru.etysoft.dira.requests.handlers;

import ru.etysoft.dira.requests.Request;
import ru.etysoft.dira.requests.RequestType;
import ru.etysoft.dira.sockets.ClientHandlerContract;
import ru.etysoft.dira.sockets.MasterSocketContract;
import ru.etysoft.dira.sockets.RoomUpdatesPool;
import ru.etysoft.dira.updates.AcceptedStatusAnswer;

public abstract class RequestHandler {

    private Request request;
    private ClientHandlerContract clientHandlerContract;
    private MasterSocketContract masterSocketContract;

    public RequestHandler(Request request, ClientHandlerContract clientHandlerContract, MasterSocketContract masterSocketContract) {
        this.request = request;
        this.clientHandlerContract = clientHandlerContract;
        this.masterSocketContract = masterSocketContract;
    }

    public void sendRequestAcceptedStatus(boolean isAccepted) {
        clientHandlerContract.sendUpdate(new AcceptedStatusAnswer(0, isAccepted).setOriginRequestId(getRequestId()));
    }

    public long getRequestId() {
        return request.getRequestId();
    }

    public abstract void process();

    public RequestType getRequestType() {
        return request.getRequestType();
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public ClientHandlerContract getClientHandlerContract() {
        return clientHandlerContract;
    }

    public void setClientHandlerContract(ClientHandlerContract clientHandlerContract) {
        this.clientHandlerContract = clientHandlerContract;
    }

    public MasterSocketContract getMasterSocketContract() {
        return masterSocketContract;
    }

    public void setMasterSocketContract(MasterSocketContract masterSocketContract) {
        this.masterSocketContract = masterSocketContract;
    }

    public RoomUpdatesPool getUpdatesPool(String roomSecret) {
        return masterSocketContract.getUpdatesPool(roomSecret);
    }
}
