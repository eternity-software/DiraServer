package ru.etysoft.dira.requests;

public class Request {

    private long requestId;
    private RequestType requestType;

    public Request(long requestId, RequestType requestType) {
        this.requestId = requestId;
        this.requestType = requestType;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public long getRequestId() {
        return requestId;
    }
}
