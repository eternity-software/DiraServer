package ru.etysoft.dira.requests.handlers;

import ru.etysoft.dira.requests.MessageReadRequest;
import ru.etysoft.dira.requests.Request;
import ru.etysoft.dira.sockets.ClientHandlerContract;
import ru.etysoft.dira.sockets.MasterSocketContract;
import ru.etysoft.dira.updates.MessageReadUpdate;

public class MessageReadHandler extends RequestHandler {

    public MessageReadHandler(Request request, ClientHandlerContract clientHandlerContract, MasterSocketContract masterSocketContract) {
        super(request, clientHandlerContract, masterSocketContract);
    }

    @Override
    public void process() {
        MessageReadRequest request = (MessageReadRequest) getRequest();

        MessageReadUpdate update = new MessageReadUpdate(request.getUserId(), request.getReadTime(),
                request.getMessageId(), request.getRoomSecret());

        if (update.getRoomSecret().length() > 64) {
            getMasterSocketContract().getUpdatesPool(request.getRoomSecret()).registerUpdate(update);
        }
    }
}
