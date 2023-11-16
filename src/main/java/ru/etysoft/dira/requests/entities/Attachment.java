package ru.etysoft.dira.requests.entities;

public class Attachment {

    private String fileUrl;
    private long fileCreatedTime;
    private String fileName;
    private String displayFileName = "";
    private long size;

    private boolean isListened = false;
    private AttachmentType attachmentType;

    private String imagePreview;

    public String getImagePreview() {
        return imagePreview;
    }

    public void setImagePreview(String imagePreview) {
        this.imagePreview = imagePreview;
    }

    private int width = -1;

    private int height = -1;

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public long getFileCreatedTime() {
        return fileCreatedTime;
    }

    public void setFileCreatedTime(long fileCreatedTime) {
        this.fileCreatedTime = fileCreatedTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDisplayFileName() {
        return displayFileName;
    }

    public void setDisplayFileName(String displayFileName) {
        this.displayFileName = displayFileName;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public AttachmentType getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(AttachmentType attachmentType) {
        this.attachmentType = attachmentType;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isListened() {
        return isListened;
    }

    public void setListened(boolean listened) {
        isListened = listened;
    }
}
