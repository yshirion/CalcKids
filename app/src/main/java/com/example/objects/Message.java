package com.example.objects;

import java.util.UUID;

public class Message {

    long id;
    long sender, destination;
    String subject = "", text ="";
    boolean readed;

    public Message() {
    }

    public Message(long sender, long destination, String subject, String text, boolean readed) {
        this.sender = sender;
        this.destination = destination;
        this.subject = subject;
        this.text = text;
        this.readed = readed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDestination() {
        return destination;
    }

    public void setDestination(long destination) {
        this.destination = destination;
    }

    public long getSender() {
        return sender;
    }

    public void setSender(long sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isReaded() {
        return readed;
    }

    public void setReaded(boolean readed) {
        this.readed = readed;
    }
}
