package com.example.objects;

import java.util.Date;

public class Loan extends Action {
    private double currentAmount, interest;

    public Loan() {
    }

    public Loan(boolean positive, String type, double amount, Date start, long user) {
        super(positive, type, amount, start, user);
    }

    public Loan(boolean positive, String type, double amount, Date start, long user, double currentAmount, double interest) {
        super(positive, type, amount, start, user);
        this.currentAmount = currentAmount;
        this.interest = interest;
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
}
