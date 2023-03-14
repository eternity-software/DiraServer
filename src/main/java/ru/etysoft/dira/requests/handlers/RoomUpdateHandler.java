package ru.etysoft.dira.requests.handlers;

import ru.etysoft.dira.requests.Request;
import ru.etysoft.dira.requests.RoomUpdateRequest;
import ru.etysoft.dira.sockets.ClientHandlerContract;
import ru.etysoft.dira.sockets.MasterSocketContract;
import ru.etysoft.dira.updates.RoomUpdate;

public class RoomUpdateHandler extends RequestHandler{
    public RoomUpdateHandler(Request request, ClientHandlerContract clientHandlerContract, MasterSocketContract masterSocketContract) {
        super(request, clientHandlerContract, masterSocketContract);
    }

    @Override
    public void process() {
        RoomUpdateRequest roomUpdateRequest = (RoomUpdateRequest) getRequest();

        getMasterSocketContract().getUpdatesPool(roomUpdateRequest.getRoomSecret()).registerUpdate(
                new RoomUpdate(roomUpdateRequest.getBase64Picture(), roomUpdateRequest.getName()).setRoomSecret(roomUpdateRequest.getRoomSecret()));
    }
}
