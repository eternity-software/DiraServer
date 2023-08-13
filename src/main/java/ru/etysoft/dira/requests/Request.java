package ru.etysoft.dira.requests;

public class Request {

    private final long requestId;
    private final RequestType requestType;

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
