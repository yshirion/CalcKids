package com.example.objects;

import java.util.UUID;

public class Family {
    String name;
    int fId;
    double loanInterest, investLongInterest, investShortInterest;
    double MIN_INTEREST = 0.1;

    public Family(String name, int fId) {
        this.name = name;
        this.fId = fId;
        loanInterest = MIN_INTEREST;
        investLongInterest = MIN_INTEREST;
        investShortInterest = MIN_INTEREST;
    }

    public void setLoanInterest(double loanInterest) throws Exception {
        if (loanInterest < 0 || loanInterest > 0.2 ) throw new Exception();
        this.loanInterest = loanInterest;
    }

    public void setInvestLongInterest(double investLongInterest) throws Exception{
        if (investLongInterest < 0 || investLongInterest > 0.2 ) throw new Exception();
        this.investLongInterest = investLongInterest;
    }

    public void setInvestShortInterest(double investShortInterest) throws Exception{
        if (investShortInterest < 0 || investShortInterest > 0.2 ) throw new Exception();
        this.investShortInterest = investShortInterest;
    }
}
