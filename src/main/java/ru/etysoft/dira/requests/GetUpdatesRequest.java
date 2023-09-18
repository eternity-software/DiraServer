package ru.etysoft.dira.requests;

import ru.etysoft.dira.requests.entities.RoomInfo;

import java.util.ArrayList;

public class GetUpdatesRequest extends Request {

    private ArrayList<RoomInfo> roomInfoList = new ArrayList<>();

    public GetUpdatesRequest() {
        super(0, RequestType.GET_UPDATES);
    }

    public ArrayList<RoomInfo> getRoomInfoList() {
        return roomInfoList;
    }

    public void setRoomInfoList(ArrayList<RoomInfo> roomInfoList) {
        this.roomInfoList = roomInfoList;
    }

    // How it looks before

    private String roomSecret;
    private long fromUpdateId;

    public GetUpdatesRequest(String roomSecret, long fromUpdateId) {
        super(0, RequestType.GET_UPDATES);
        this.roomSecret = roomSecret;
        this.fromUpdateId = fromUpdateId;
    }

    public long getFromUpdateId() {
        return fromUpdateId;
    }

    public void setFromUpdateId(long fromUpdateId) {
        this.fromUpdateId = fromUpdateId;
    }

    public String getRoomSecret() {
        return roomSecret;
    }

    public void setRoomSecret(String roomSecret) {
        this.roomSecret = roomSecret;
    }
}
