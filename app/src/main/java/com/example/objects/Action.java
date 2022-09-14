package com.example.objects;

import java.util.Date;
import java.util.UUID;

public class Action {
    protected boolean positive;
    protected String type;
    protected double amount;
    protected Date start;
    protected UUID user;


    public Action(boolean positive, String type, double amount, Date start, UUID user) {
        this.positive = positive;
        this.type = type;
        this.amount = amount;
        this.start = start;
        this.user = user;
    }

    public boolean isPositive() {
        return positive;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public Date getStart() {
        return start;
    }

    public UUID getUser() {
        return user;
    }
}
