package com.example.objects;

import java.util.Date;

public class Invest extends Action {
    private final int SHORT = 6;
    private final int LONG = 12;
    private double currentAmount, interest;
    private Date end;
    boolean longTerm;

    public Invest() {
    }

    public Invest(boolean positive, String type, double amount, Date start, long user, boolean longTerm) {
        super(positive, type, amount, start, user);
        this.longTerm = longTerm;
        end = new Date(1,2,3);
    }

    public Invest(double currentAmount, double interest, Date end, boolean longTerm) {
        this.currentAmount = currentAmount;
        this.interest = interest;
        this.end = end;
        this.longTerm = longTerm;
    }

    public Invest(boolean positive, String type, double amount, Date start, long user, double currentAmount, double interest, Date end, boolean longTerm) {
        super(positive, type, amount, start, user);
        this.currentAmount = currentAmount;
        this.interest = interest;
        this.end = end;
        this.longTerm = longTerm;
    }

    public int getSHORT() {
        return SHORT;
    }

    public int getLONG() {
        return LONG;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public boolean isLongTerm() {
        return longTerm;
    }

    public void setLongTerm(boolean longTerm) {
        this.longTerm = longTerm;
    }
}

