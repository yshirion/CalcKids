package com.example.objects;

public class Family {
    long fId;
    String name;
    double loanInterest, investLongInterest, investShortInterest; // percents of interest for each one.
    final double MIN_INTEREST = 0.1;

    public Family(String name, long fId) {
        this.name = name;
        this.fId = fId;
        loanInterest = MIN_INTEREST;
        investLongInterest = MIN_INTEREST;
        investShortInterest = MIN_INTEREST;
    }

    public Family() {
    }

    public Family(String name, long fId, double loanInterest,
                  double investLongInterest, double investShortInterest) {
        this.name = name;
        this.fId = fId;
        this.loanInterest = loanInterest;
        this.investLongInterest = investLongInterest;
        this.investShortInterest = investShortInterest;
    }

    public long getfId() {
        return fId;
    }

    public void setfId(long fId) {
        this.fId = fId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLoanInterest() {
        return loanInterest;
    }

    public void setLoanInterest(double loanInterest) {
        this.loanInterest = loanInterest;
    }

    public double getInvestLongInterest() {
        return investLongInterest;
    }

    public void setInvestLongInterest(double investLongInterest) {
        this.investLongInterest = investLongInterest;
    }

    public double getInvestShortInterest() {
        return investShortInterest;
    }

    public void setInvestShortInterest(double investShortInterest) {
        this.investShortInterest = investShortInterest;
    }

    public double getMIN_INTEREST() {
        return MIN_INTEREST;
    }
}
