package com.example.objects;

import android.os.Build;
import androidx.annotation.RequiresApi;
import java.time.LocalDateTime;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Loan extends Action {
    private long lid;
    private double currentAmount, interest;
    private LocalDateTime updateTime;

    public Loan() {
    }

    public Loan(boolean positive, String type, double amount, long user) {
        super(positive, type, amount, user);
        lid = 0;
        currentAmount = amount;
        updateTime = LocalDateTime.now();
    }

    public Loan(long lid, boolean positive, String type, double amount,
                long user, double currentAmount, double interest, LocalDateTime updateTime) {
        super(positive, type, amount, user);
        this.lid = lid;
        this.currentAmount = currentAmount;
        this.interest = interest;
        this.updateTime = updateTime;
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

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public long getLid() {
        return lid;
    }

    public void setLid(long lid) {
        this.lid = lid;
    }
}
