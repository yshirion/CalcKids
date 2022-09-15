package com.example.objects;

import java.util.Date;
import java.util.UUID;

public class Loan extends Action {
    private double currentAmount, interest;

    public Loan(UUID user, double amount, double interestPercent, Date date){
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
