package com.example.objects;

import java.util.Date;

public class Loan extends Action {
    private double currentAmount, interest;

    public Loan(User user, double amount, double interestPercent, Date date){
        super(true,"loan",amount,date,user);
        interest = interestPercent;

    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public double getInterest() {
        return interest;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }
}
