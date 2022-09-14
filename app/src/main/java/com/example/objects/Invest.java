package com.example.objects;

import java.util.Calendar;
import java.util.Date;

public class Invest extends Action {
    private final int SHORT = 6;
    private final int LONG = 12;
    private double currentAmount, interest;
    private Date end;
    boolean longTerm;


    public Invest(User user, double amount, Date date, boolean longT){
        super(false,"invest",amount,date,user);
        longTerm = longT;
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        if (longT) {
            cal.add(Calendar.MONTH, LONG);
        }
        else
            cal.add(Calendar.MONTH, SHORT);
        end = cal.getTime();
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public double getInterest() {
        return interest;
    }

    public Date getEnd() {
        return end;
    }

    public boolean isLongTerm() {
        return longTerm;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }
}
