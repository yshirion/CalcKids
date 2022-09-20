package com.example.objects;

import java.util.Date;

public class Action {
    protected boolean positive;
    protected String type;
    protected double amount;
    protected Date start;
    protected long user;

    public Action() {
    }

    public Action(boolean positive, String type, double amount, Date start, long user) {
        this.positive = positive;
        this.type = type;
        this.amount = amount;
        this.start = start;
        this.user = user;
    }


    public boolean isPositive() {
        return positive;
    }

    public void setPositive(boolean positive) {
        this.positive = positive;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }
}
