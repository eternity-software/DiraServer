package ru.etysoft.dira.requests.handlers;

import ru.etysoft.dira.requests.CreateInviteRequest;
import ru.etysoft.dira.requests.JoinRoomRequest;
import ru.etysoft.dira.requests.Request;
import ru.etysoft.dira.requests.entities.InviteRoom;
import ru.etysoft.dira.sockets.ClientHandlerContract;
import ru.etysoft.dira.sockets.MasterSocketContract;
import ru.etysoft.dira.updates.NewInvitationUpdate;
import ru.etysoft.dira.updates.NewRoomUpdate;
import ru.etysoft.dira.utils.KeyGenerator;

import java.security.SecureRandom;
import java.util.HashMap;

public class InviteHandler extends RequestHandler {

    public static HashMap<String, InviteRoom> invitations = new HashMap<>();

    public InviteHandler(Request request, ClientHandlerContract clientHandlerContract, MasterSocketContract masterSocketContract) {
        super(request, clientHandlerContract, masterSocketContract);
    }

    @Override
    public void process() {

        if(getRequest() instanceof JoinRoomRequest)
        {
            if(invitations.containsKey(((JoinRoomRequest) getRequest()).getInvitationCode()))
            {
                InviteRoom inviteRoom = invitations.get(((JoinRoomRequest) getRequest()).getInvitationCode());

                NewRoomUpdate newRoomUpdate = new NewRoomUpdate(inviteRoom);
                newRoomUpdate.setOriginRequestId(getRequestId());

                getClientHandlerContract().sendUpdate(newRoomUpdate);
            }
        }
        else if(getRequest() instanceof CreateInviteRequest) {

            CreateInviteRequest createInviteRequest = (CreateInviteRequest) getRequest();

            InviteRoom inviteRoom = new InviteRoom(createInviteRequest.getRoomName(), createInviteRequest.getRoomSecret(),
                    createInviteRequest.getBase64pic(), createInviteRequest.getMemberList());

            String invitationCode = KeyGenerator.generateString(new SecureRandom(), 16);

            invitations.put(invitationCode, inviteRoom);

            NewInvitationUpdate newInvitationUpdate = new NewInvitationUpdate(invitationCode);
            newInvitationUpdate.setOriginRequestId(createInviteRequest.getRequestId());

            getMasterSocketContract().getUpdatesPool(createInviteRequest.getRoomSecret()).registerUpdate(newInvitationUpdate);
        }

    }
}
