package ru.etysoft.dira.requests.handlers;

import ru.etysoft.dira.requests.PinnedMessageAddedRequest;
import ru.etysoft.dira.requests.PinnedMessageRemovedRequest;
import ru.etysoft.dira.requests.Request;
import ru.etysoft.dira.sockets.ClientHandlerContract;
import ru.etysoft.dira.sockets.MasterSocketContract;
import ru.etysoft.dira.updates.PinnedMessageAddedUpdate;
import ru.etysoft.dira.updates.PinnedMessageRemovedUpdate;

public class PinnedMessageRemovedHandler extends RequestHandler {
    public PinnedMessageRemovedHandler(Request request, ClientHandlerContract clientHandlerContract, MasterSocketContract masterSocketContract) {
        super(request, clientHandlerContract, masterSocketContract);
    }

    @Override
    public void process() {
        PinnedMessageRemovedRequest request = (PinnedMessageRemovedRequest) getRequest();

        PinnedMessageRemovedUpdate update =
                new PinnedMessageRemovedUpdate(request.getRoomSecret(), request.getMessageId(), request.getUserId());

        if (request.getRoomSecret().length() > 64) {
            getMasterSocketContract().getUpdatesPool(request.getRoomSecret()).registerUpdate(update);
        }
    }
}
