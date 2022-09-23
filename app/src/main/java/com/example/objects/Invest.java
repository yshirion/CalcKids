package com.example.objects;

import android.os.Build;
import androidx.annotation.RequiresApi;
import java.time.LocalDateTime;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Invest extends Action {
    private final int SHORT = 6;
    private final int LONG = 12;
    private double currentAmount, interest;
    private LocalDateTime end;
    private LocalDateTime updateTime;
    boolean longTerm; //if the investment is to long term or short.

    public Invest() {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Invest(boolean positive, String type, double amount, long user, boolean longTerm) {
        super(positive, type, amount, user);
        this.longTerm = longTerm;
        currentAmount = amount;
        updateTime = LocalDateTime.now();
        //Set end of invest based on type of invest.
        int gap;
        if (longTerm) gap = LONG;
        else gap = SHORT;
        end = LocalDateTime.now().plusMonths(gap);
    }

    public Invest(boolean positive, String type, double amount,long user, double currentAmount,
                  double interest, LocalDateTime end, boolean longTerm, LocalDateTime updateTime) {
        super(positive, type, amount, user);
        this.currentAmount = currentAmount;
        this.interest = interest;
        this.end = end;
        this.longTerm = longTerm;
        this.updateTime = updateTime;
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

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public boolean isLongTerm() {
        return longTerm;
    }

    public void setLongTerm(boolean longTerm) {
        this.longTerm = longTerm;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}

