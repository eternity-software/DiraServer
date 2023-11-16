package ru.etysoft.dira.requests.handlers;

import ru.etysoft.dira.requests.AttachmentListenedRequest;
import ru.etysoft.dira.requests.Request;
import ru.etysoft.dira.requests.SendUserStatusRequest;
import ru.etysoft.dira.sockets.ClientHandlerContract;
import ru.etysoft.dira.sockets.MasterSocketContract;
import ru.etysoft.dira.updates.AttachmentListenedUpdate;
import ru.etysoft.dira.updates.UserStatusUpdate;

public class AttachmentListenedHandler extends RequestHandler {

    public AttachmentListenedHandler(Request request, ClientHandlerContract clientHandlerContract, MasterSocketContract masterSocketContract) {
        super(request, clientHandlerContract, masterSocketContract);
    }

    @Override
    public void process() {
        AttachmentListenedRequest request = (AttachmentListenedRequest) getRequest();

        AttachmentListenedUpdate update =
                new AttachmentListenedUpdate(request.getRoomSecret(), request.getMessageId(), request.getUserId());

        if (request.getRoomSecret().length() > 64) {
            getMasterSocketContract().getUpdatesPool(request.getRoomSecret()).registerUpdate(update);
        }
    }
}
