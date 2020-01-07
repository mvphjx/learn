package com.learn.hanjx.design.ddd;

// @Entity
public class Account {
    // @Id
    private String id;
    private double balance;
    private String overdraftCode;
    private OverdraftPolicy overdraftPolicy;
    public double balance() { return balance; }
    public void debit(double amount) {
        this.overdraftPolicy.preDebit(this, amount);
        this.balance = this.balance - amount;
        this.overdraftPolicy.postDebit(this, amount);
    }
    public void credit(double amount) {
        this.balance = this.balance + amount;
    }

    public double getBalance()
    {
        return balance;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    public OverdraftPolicy getOverdraftPolicy()
    {
        return overdraftPolicy;
    }

    public void setOverdraftPolicy(OverdraftPolicy overdraftPolicy)
    {
        this.overdraftPolicy = overdraftPolicy;
    }

    public String getOverdraftCode()
    {
        return overdraftCode;
    }

    public void setOverdraftCode(String overdraftCode)
    {
        this.overdraftCode = overdraftCode;
    }
}
