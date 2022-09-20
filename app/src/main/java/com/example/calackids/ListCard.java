package com.example.calackids;

public class ListCard {
    String date, amount_from, subject_type, end_read;
    public boolean positive;

    public ListCard(String date, String amount_from, String subject_type, String end_read) {
        this.date = date;
        this.amount_from = amount_from;
        this.subject_type = subject_type;
        this.end_read = end_read;
    }

    public ListCard() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount_from() {
        return amount_from;
    }

    public void setAmount_from(String amount_from) {
        this.amount_from = amount_from;
    }

    public String getSubject_type() {
        return subject_type;
    }

    public void setSubject_type(String subject_type) {
        this.subject_type = subject_type;
    }

    public String getEnd_read() {
        return end_read;
    }

    public void setEnd_read(String end_read) {
        this.end_read = end_read;
    }
}
