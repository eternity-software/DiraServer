package ru.etysoft.dira.requests.handlers;

import ru.etysoft.dira.requests.SendMessageRequest;
import ru.etysoft.dira.sockets.ClientHandlerContract;
import ru.etysoft.dira.sockets.MasterSocketContract;
import ru.etysoft.dira.updates.NewMessageUpdate;
import ru.etysoft.dira.updates.Update;

public class NewMessageHandler extends RequestHandler {
    public NewMessageHandler(SendMessageRequest request, ClientHandlerContract clientHandlerContract, MasterSocketContract masterSocketContract) {
        super(request, clientHandlerContract, masterSocketContract);
    }

    @Override
    public void process() {
        SendMessageRequest sendMessageRequest = (SendMessageRequest) getRequest();

        if(sendMessageRequest.getExpireSec() == 0)
            sendMessageRequest.setExpireSec(Update.DEFAULT_UPDATE_EXPIRE_SEC);

        NewMessageUpdate newMessageUpdate = new NewMessageUpdate(sendMessageRequest.getMessage());
        newMessageUpdate.getMessage().setTime(System.currentTimeMillis());
        newMessageUpdate.setUpdateExpireSec(sendMessageRequest.getExpireSec());


        if (newMessageUpdate.getMessage().getId().length() > 12 && newMessageUpdate.getMessage().getRoomSecret().length() > 64) {
            getMasterSocketContract().getUpdatesPool(sendMessageRequest.getMessage().getRoomSecret()).registerUpdate(newMessageUpdate);
        }

    }
}
