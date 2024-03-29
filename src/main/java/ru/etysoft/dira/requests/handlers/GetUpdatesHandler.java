package ru.etysoft.dira.requests.handlers;

import ru.etysoft.dira.requests.GetUpdatesRequest;
import ru.etysoft.dira.requests.Request;
import ru.etysoft.dira.requests.entities.RoomInfo;
import ru.etysoft.dira.sockets.ClientHandlerContract;
import ru.etysoft.dira.sockets.MasterSocketContract;
import ru.etysoft.dira.sockets.RoomUpdatesPool;
import ru.etysoft.dira.updates.AcceptedStatusAnswer;
import ru.etysoft.dira.updates.Update;

public class GetUpdatesHandler extends RequestHandler {
    public GetUpdatesHandler(Request request, ClientHandlerContract clientHandlerContract, MasterSocketContract masterSocketContract) {
        super(request, clientHandlerContract, masterSocketContract);
    }

    @Override
    public void process() {
        GetUpdatesRequest getUpdatesRequest = (GetUpdatesRequest) getRequest();

        if (getUpdatesRequest.getRoomInfoList().size() == 0) {
            RoomUpdatesPool roomUpdatesPool = getMasterSocketContract().getUpdatesPool(getUpdatesRequest.getRoomSecret());

            for (Update update : roomUpdatesPool.getUpdatesAfter(getUpdatesRequest.getFromUpdateId())) {
                getClientHandlerContract().sendUpdate(update);
            }
        } else {

            for (RoomInfo roomInfo: getUpdatesRequest.getRoomInfoList()) {
                RoomUpdatesPool roomUpdatesPool = getMasterSocketContract().getUpdatesPool(roomInfo.getRoomSecret());

                for (Update update : roomUpdatesPool.getUpdatesAfter(roomInfo.getFromUpdateId())) {
                    getClientHandlerContract().sendUpdate(update);
                }
            }
        }

        AcceptedStatusAnswer acceptedStatusAnswer = new AcceptedStatusAnswer(0, true);
        acceptedStatusAnswer.setOriginRequestId(getUpdatesRequest.getRequestId());

        getClientHandlerContract().sendUpdate(acceptedStatusAnswer);

    }
}
