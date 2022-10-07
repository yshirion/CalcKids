package com.example.objects;

import android.os.Build;
import androidx.annotation.RequiresApi;
import java.time.LocalDateTime;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Action {
    protected boolean positive;
    protected String type;
    protected double amount;
    protected LocalDateTime start;
    protected long user;

    public Action() {
    }

    public Action(boolean positive, String type, double amount, long user) {

        this.positive = positive;
        this.type = type;
        this.amount = amount;
        start = LocalDateTime.now();
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

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }
}
