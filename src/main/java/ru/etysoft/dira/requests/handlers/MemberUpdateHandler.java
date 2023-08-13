package ru.etysoft.dira.requests.handlers;

import ru.etysoft.dira.requests.Request;
import ru.etysoft.dira.requests.UpdateMemberRequest;
import ru.etysoft.dira.sockets.ClientHandlerContract;
import ru.etysoft.dira.sockets.MasterSocketContract;
import ru.etysoft.dira.updates.MemberUpdate;

public class MemberUpdateHandler extends RequestHandler {
    public MemberUpdateHandler(Request request, ClientHandlerContract clientHandlerContract, MasterSocketContract masterSocketContract) {
        super(request, clientHandlerContract, masterSocketContract);
    }

    @Override
    public void process() {
        UpdateMemberRequest updateMemberRequest = (UpdateMemberRequest) getRequest();


        for (String roomSecret : updateMemberRequest.getRoomSecrets()) {
            MemberUpdate memberUpdate = new MemberUpdate(updateMemberRequest.getNickname(),
                    updateMemberRequest.getBase64pic(), updateMemberRequest.getId(), updateMemberRequest.getUpdateTime());
            memberUpdate.setRoomSecret(roomSecret);
            getMasterSocketContract().getUpdatesPool(roomSecret).registerUpdate(memberUpdate);
        }


    }
}
