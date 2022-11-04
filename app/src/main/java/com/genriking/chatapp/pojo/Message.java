package com.genriking.chatapp.pojo;

public class Message {
    private String author;
    private String textOfMessage;
    private long date;
    private String imageUrl;

    public Message(String author, String textOfMessage, long date, String imageUrl) {
        this.author = author;
        this.textOfMessage = textOfMessage;
        this.date = date;
        this.imageUrl = imageUrl;
    }

    public Message() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTextOfMessage() {
        return textOfMessage;
    }

    public void setTextOfMessage(String textOfMassage) {
        this.textOfMessage = textOfMassage;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
