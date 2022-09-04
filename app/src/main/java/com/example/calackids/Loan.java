package com.example.calackids;

import java.util.Date;

public class Loan {
    private double loanAmount, currentAmount, interest;
    private Date start;

    public Loan(double amount, double interestPercent, Date dateOfTake){
        loanAmount = amount;
        interest = interestPercent;
        start = dateOfTake;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }
}
