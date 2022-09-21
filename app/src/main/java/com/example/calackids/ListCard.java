package com.example.calackids;

import com.example.objects.Message;

import java.io.Serializable;

public class ListCard implements Serializable {
    String date, amount_from, subject_type, end_read;
    Message message;
    public boolean isBalance, isPositive, isMessage, isReaded;

    public ListCard(String date, String amount_from, String subject_type, String end_read) {
        this.date = date;
        this.amount_from = amount_from;
        this.subject_type = subject_type;
        this.end_read = end_read;
    }

    public ListCard(String date, String amount_from, String subject_type, String end_read, Message message) {
        this.date = date;
        this.amount_from = amount_from;
        this.subject_type = subject_type;
        this.end_read = end_read;
        this.message = message;
    }


    public String getDate() {
        return date;
    }

    public String getAmount_from() {
        return amount_from;
    }

    public String getSubject_type() {
        return subject_type;
    }

    public String getEnd_read() {
        return end_read;
    }

    public Message getMessage() {
        return message;
    }
}
