package ru.etysoft.dira.requests.handlers;

import ru.etysoft.dira.requests.Request;
import ru.etysoft.dira.requests.encryption.SubmitKeyRequest;
import ru.etysoft.dira.requests.entities.BaseMember;
import ru.etysoft.dira.sockets.ClientHandlerContract;
import ru.etysoft.dira.sockets.MasterSocketContract;

public class SubmitKeyHandler extends RequestHandler {
    public SubmitKeyHandler(Request request, ClientHandlerContract clientHandlerContract, MasterSocketContract masterSocketContract) {
        super(request, clientHandlerContract, masterSocketContract);
    }

    @Override
    public void process() {
        SubmitKeyRequest submitKeyRequest = (SubmitKeyRequest) getRequest();
        BaseMember baseMember = submitKeyRequest.getBaseMember();

        KeyRenewHandler.addConfirmedMember(submitKeyRequest.getRoomSecret(), baseMember);

        KeyRenewHandler.checkRenewingStatus(submitKeyRequest.getRoomSecret(), this);

    }
}
