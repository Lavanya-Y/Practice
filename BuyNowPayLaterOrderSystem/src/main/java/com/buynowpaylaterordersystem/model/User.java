package com.buynowpaylaterordersystem.model;

public class User {
    String username;
    Double bnplCredit;
    Double bnplCreditLimit;
    Boolean isBnplBlocked;

    public User(String username, Double bnplCredit, Double bnplCreditLimit, Boolean isBnplBlocked) {
        this.username = username;
        this.bnplCredit = bnplCredit;
        this.bnplCreditLimit = bnplCreditLimit;
        this.isBnplBlocked = isBnplBlocked;
    }

    public Double reduceBnplCredits(Double bnplCredit) {
        this.bnplCredit -= bnplCredit;
        if (this.bnplCredit == 0.0) {
            this.isBnplBlocked = true;
        }
        return bnplCredit;
    }

    public Double addBnplCredits(Double bnplCredit) {
        this.bnplCredit += bnplCredit;
        if (this.bnplCredit > 0.0) {
            this.isBnplBlocked = false;
        }
        return bnplCredit;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getBnplCredit() {
        return bnplCredit;
    }

    public void setBnplCredit(Double bnplCredit) {
        this.bnplCredit = bnplCredit;
    }

    public Double getBnplCreditLimit() {
        return bnplCreditLimit;
    }

    public void setBnplCreditLimit(Double bnplCreditLimit) {
        this.bnplCreditLimit = bnplCreditLimit;
    }

    public Boolean getBnplBlocked() {
        return isBnplBlocked;
    }

    public void setBnplBlocked(Boolean bnplBlocked) {
        isBnplBlocked = bnplBlocked;
    }
}
