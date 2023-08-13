package ru.etysoft.dira.requests.handlers;

import ru.etysoft.dira.requests.PingReactRequest;
import ru.etysoft.dira.requests.entities.BaseMember;
import ru.etysoft.dira.sockets.ClientHandlerContract;
import ru.etysoft.dira.sockets.MasterSocketContract;
import ru.etysoft.dira.updates.BaseMemberUpdate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PingReactHandler extends RequestHandler {

    private static final HashMap<String, List<BaseMember>> pingRoomsMembers = new HashMap<>();

    public PingReactHandler(PingReactRequest request, ClientHandlerContract clientHandlerContract, MasterSocketContract masterSocketContract) {
        super(request, clientHandlerContract, masterSocketContract);
    }

    public static void clearBaseMembers(String roomSecret) {
        pingRoomsMembers.remove(roomSecret);
    }

    public static List<BaseMember> getBaseMembers(String roomSecret) {
        return pingRoomsMembers.get(roomSecret);
    }

    @Override
    public void process() {
        PingReactRequest pingMembersRequest = (PingReactRequest) getRequest();

        BaseMember baseMember = pingMembersRequest.getBaseMember();

        List<BaseMember> baseMembers = new ArrayList<>();

        if (pingRoomsMembers.containsKey(pingMembersRequest.getRoomSecret())) {
            baseMembers = pingRoomsMembers.get(pingMembersRequest.getRoomSecret());
        }

        for (BaseMember pendingBaseMember : baseMembers) {
            if (baseMember.getId().equals(pendingBaseMember.getId())) return;
        }

        baseMembers.add(baseMember);

        pingRoomsMembers.put(pingMembersRequest.getRoomSecret(), baseMembers);

        getUpdatesPool(pingMembersRequest.getRoomSecret()).registerUpdate(
                new BaseMemberUpdate(baseMember).setRoomSecret(pingMembersRequest.getRoomSecret()));
        sendRequestAcceptedStatus(true);
    }
}