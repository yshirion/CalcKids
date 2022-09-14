package com.example.objects;

import java.util.UUID;

public class Message {

    UUID sender, destination;
    String subject = "", text ="";

    public Message(UUID from, UUID destination, String subject, String text) {
        this.sender = from;
        this.destination = destination;
        this.subject = subject;
        this.text = text;
    }

    public UUID getSender() {
        return sender;
    }

    public UUID getDestination() {
        return destination;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }
}
