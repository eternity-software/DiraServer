package ru.etysoft.dira.requests.handlers;

import ru.etysoft.dira.requests.Request;
import ru.etysoft.dira.requests.SendUserStatusRequest;
import ru.etysoft.dira.sockets.ClientHandlerContract;
import ru.etysoft.dira.sockets.MasterSocketContract;
import ru.etysoft.dira.updates.UserStatusUpdate;

public class UserStatusHandler extends RequestHandler {

    public UserStatusHandler(Request request, ClientHandlerContract clientHandlerContract, MasterSocketContract masterSocketContract) {
        super(request, clientHandlerContract, masterSocketContract);
    }

    @Override
    public void process() {
        SendUserStatusRequest request = (SendUserStatusRequest) getRequest();

        UserStatusUpdate update = new UserStatusUpdate(request.getStatus());

        if (request.getStatus().getSecretName().length() > 64) {
            getMasterSocketContract().getUpdatesPool(request.getStatus().getSecretName()).registerUpdate(update);
        }
    }
}
