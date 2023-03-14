package ru.etysoft.dira.updates;

import ru.etysoft.dira.requests.entities.InviteRoom;

public class NewRoomUpdate extends Update {

    private InviteRoom inviteRoom;

    public NewRoomUpdate( InviteRoom inviteRoom) {
        super(0, UpdateType.NEW_ROOM_UPDATE);
        this.inviteRoom = inviteRoom;
    }

    public InviteRoom getInviteRoom() {
        return inviteRoom;
    }

    public void setInviteRoom(InviteRoom inviteRoom) {
        this.inviteRoom = inviteRoom;
    }
}
