package ru.etysoft.dira.requests.handlers;

import ru.etysoft.dira.requests.PingMembersRequest;
import ru.etysoft.dira.requests.Request;
import ru.etysoft.dira.requests.encryption.SendIntermediateKey;
import ru.etysoft.dira.sockets.ClientHandlerContract;
import ru.etysoft.dira.sockets.MasterSocketContract;
import ru.etysoft.dira.updates.KeyReceivedUpdate;
import ru.etysoft.dira.updates.PingUpdate;

import java.util.HashMap;

public class IntermediateKeyHandler extends RequestHandler {

    public static final HashMap<String, HashMap<String, Integer>> updateCounter = new HashMap<>();
    public IntermediateKeyHandler(SendIntermediateKey request, ClientHandlerContract clientHandlerContract, MasterSocketContract masterSocketContract) {
        super(request, clientHandlerContract, masterSocketContract);
    }

    @Override
    public void process() {
        SendIntermediateKey sendIntermediateKey = (SendIntermediateKey) getRequest();

        HashMap<String, Integer> membersCounter = new HashMap<>();

        if(updateCounter.containsKey(sendIntermediateKey.getRoomSecret()))
        {
            membersCounter = updateCounter.get(sendIntermediateKey.getRoomSecret());
        }

        int count = 0;

        if(membersCounter.containsKey(sendIntermediateKey.getDhKey().getRecipientMemberId()))
        {
            count = membersCounter.get(sendIntermediateKey.getDhKey().getRecipientMemberId());
        }

        count++;
        membersCounter.put(sendIntermediateKey.getDhKey().getRecipientMemberId(), count);
        updateCounter.put(sendIntermediateKey.getRoomSecret(), membersCounter);

        getUpdatesPool(sendIntermediateKey.getRoomSecret()).registerUpdate(
                new KeyReceivedUpdate(sendIntermediateKey.getDhKey()).setRoomSecret(sendIntermediateKey.getRoomSecret()));
        sendRequestAcceptedStatus(true);
    }
}
