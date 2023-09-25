package ru.etysoft.dira.requests;

import ru.etysoft.dira.requests.entities.Message;

public class SendMessageRequest extends Request {

    private Message message;

    private int expireSec;


    public SendMessageRequest(Message message, int expireSec) {
        super(0, RequestType.SEND_MESSAGE_REQUEST);
        this.message = message;
        this.expireSec = expireSec;
    }

    public int getExpireSec() {
        return expireSec;
    }

    public void setExpireSec(int expireSec) {
        this.expireSec = expireSec;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
