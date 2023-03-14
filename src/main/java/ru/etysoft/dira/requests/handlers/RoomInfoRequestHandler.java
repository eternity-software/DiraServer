package ru.etysoft.dira.requests.handlers;

import ru.etysoft.dira.requests.Request;
import ru.etysoft.dira.requests.RequestType;
import ru.etysoft.dira.requests.VerifyRoomInfoRequest;
import ru.etysoft.dira.sockets.ClientHandlerContract;
import ru.etysoft.dira.sockets.MasterSocketContract;

public class RoomInfoRequestHandler extends RequestHandler {

    public RoomInfoRequestHandler(Request request, ClientHandlerContract clientHandlerContract, MasterSocketContract masterSocketContract) {
        super(request, clientHandlerContract, masterSocketContract);
    }

    @Override
    public void process()
    {
        if(getRequestType() == RequestType.VERIFY_ROOM_INFO)
        {
            VerifyRoomInfoRequest verifyRoomInfoRequest = (VerifyRoomInfoRequest) getRequest();
            if(verifyRoomInfoRequest.getRoomSecret().length() > 64 && verifyRoomInfoRequest.getName().length() > 1)
            {
                answerAccepted(true);
            }
            else
            {
                answerAccepted(false);
            }
        }
    }

}
