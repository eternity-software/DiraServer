package ru.etysoft.dira.requests.handlers;


import ru.etysoft.dira.requests.PingMembersRequest;
import ru.etysoft.dira.sockets.ClientHandlerContract;
import ru.etysoft.dira.sockets.MasterSocketContract;
import ru.etysoft.dira.updates.PingUpdate;

public class PingMembersHandler extends RequestHandler {
    public PingMembersHandler(PingMembersRequest request, ClientHandlerContract clientHandlerContract, MasterSocketContract masterSocketContract) {
        super(request, clientHandlerContract, masterSocketContract);
    }

    @Override
    public void process() {
        PingMembersRequest pingMembersRequest = (PingMembersRequest) getRequest();

        PingReactHandler.clearBaseMembers(pingMembersRequest.getRoomSecret());

        getUpdatesPool(pingMembersRequest.getRoomSecret()).registerUpdate(
                new PingUpdate().setRoomSecret(pingMembersRequest.getRoomSecret()));
        sendRequestAcceptedStatus(true);
    }
}