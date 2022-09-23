package com.example.objects;

import android.os.Build;
import androidx.annotation.RequiresApi;
import java.io.Serializable;
import java.time.LocalDateTime;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Message implements Serializable {

    long id;
    long sender, destination;
    String subject = "", text ="";
    String sender_name;
    LocalDateTime today;
    boolean readed;

    public Message() {
    }

    public Message(long sender, long destination, String subject, String text, boolean readed) {
        this.sender = sender;
        this.destination = destination;
        this.subject = subject;
        this.text = text;
        this.readed = readed;
        today = LocalDateTime.now();
    }

    public Message(long id, long sender, long destination, String subject, String text, String sender_name, LocalDateTime today, boolean readed) {
        this.id = id;
        this.sender = sender;
        this.destination = destination;
        this.subject = subject;
        this.text = text;
        this.sender_name = sender_name;
        this.today = today;
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

    public LocalDateTime getToday() {
        return today;
    }

    public void setToday() {
        today = LocalDateTime.now();
    }

    public String getSender_name() {
        return sender_name;
    }

    public void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }

    public void setToday(LocalDateTime today) {
        this.today = today;
    }
}
