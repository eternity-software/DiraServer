package ru.etysoft.dira.requests;

import ru.etysoft.dira.requests.entities.Status;

public class SendUserStatusRequest extends Request {

    private Status status;

    public SendUserStatusRequest(Status status) {
        super(0, RequestType.USER_STATUS_REQUEST);
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
}
