package ru.etysoft.dira.requests;

import ru.etysoft.dira.requests.entities.UserStatus;

public class SendUserStatusRequest extends Request {

    private final UserStatus userStatus;

    public SendUserStatusRequest(UserStatus userStatus) {
        super(0, RequestType.USER_STATUS_REQUEST);
        this.userStatus = userStatus;
    }

    public UserStatus getStatus() {
        return userStatus;
    }
}
