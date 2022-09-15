package com.example.objects;

import java.util.UUID;

public class Family {
    String name;
    int fId;
    double loanInterest, investLongInterest, investShortInterest;

    public Family(String name, int fId) {
        this.name = name;
        this.fId = fId;
        loanInterest = 0.01;
        investLongInterest = 0.01;
        investShortInterest = 0.005;
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
