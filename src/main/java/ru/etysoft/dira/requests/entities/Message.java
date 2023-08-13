package ru.etysoft.dira.requests.entities;

import java.util.ArrayList;
import java.util.List;

public class Message {


    private String id;
    private String authorId;
    private String roomSecret;
    private String text;
    private String authorNickname;
    private long time;
    private List<Attachment> attachments = new ArrayList<>();
    private long lastTimeAuthorUpdated;
    private long lastTimeEncryptionKeyUpdated;

    public Message(String authorId, String text, String authorNickname) {
        this.authorId = authorId;
        this.text = text;
        this.authorNickname = authorNickname;
    }

    public Message() {

    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public String getRoomSecret() {
        return roomSecret;
    }

    public void setRoomSecret(String roomSecret) {
        this.roomSecret = roomSecret;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthorNickname() {
        return authorNickname;
    }

    public void setAuthorNickname(String authorNickname) {
        this.authorNickname = authorNickname;
    }
}
