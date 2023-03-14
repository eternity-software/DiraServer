package ru.etysoft.dira.requests;

import ru.etysoft.dira.requests.entities.Message;

public class SendMessageRequest extends Request {

    private Message message;


    public SendMessageRequest(Message message) {
        super(0, RequestType.SEND_MESSAGE_REQUEST);
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
