package com.example.objects;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
@RequiresApi(api = Build.VERSION_CODES.O)
public class Loan extends Action {
    private double currentAmount, interest;

    public Loan() {
    }

    public Loan(boolean positive, String type, double amount, long user) {
        super(positive, type, amount, user);
        currentAmount = amount;
    }

    public Loan(boolean positive, String type, double amount, long user, double currentAmount, double interest) {
        super(positive, type, amount, user);
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
