package com.example.objects;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Invest extends Action {
    private final int SHORT = 6;
    private final int LONG = 12;
    private double currentAmount, interest;
    private LocalDate end;
    boolean longTerm;

    public Invest() {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Invest(boolean positive, String type, double amount, long user, boolean longTerm) {
        super(positive, type, amount, user);
        this.longTerm = longTerm;
        currentAmount = amount;
        //Set end of invest based on type of invest.
        int gap;
        if (longTerm) gap = LONG;
        else gap = SHORT;
        end = LocalDate.now().plusMonths(gap);
    }

    public Invest(double currentAmount, double interest, LocalDate end, boolean longTerm) {
        this.currentAmount = currentAmount;
        this.interest = interest;
        this.end = end;
        this.longTerm = longTerm;
    }

    public Invest(boolean positive, String type, double amount,long user, double currentAmount, double interest, LocalDate end, boolean longTerm) {
        super(positive, type, amount, user);
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

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public boolean isLongTerm() {
        return longTerm;
    }

    public void setLongTerm(boolean longTerm) {
        this.longTerm = longTerm;
    }
}

