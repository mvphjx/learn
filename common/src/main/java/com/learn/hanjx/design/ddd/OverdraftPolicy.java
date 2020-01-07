package com.learn.hanjx.design.ddd;

public interface OverdraftPolicy
{
    void preDebit(Account account, double amount);

    void postDebit(Account account, double amount);
}

class NoOverdraftAllowed implements OverdraftPolicy
{
    public void preDebit(Account account, double amount)
    {
        double newBalance = account.balance() - amount;
        if (newBalance < 0)
        {
            throw new RuntimeException("Insufficient funds");
        }
    }

    public void postDebit(Account account, double amount)
    {
    }
}

class LimitedOverdraft implements OverdraftPolicy
{
    private double limit;

    public void preDebit(Account account, double amount)
    {
        double newBalance = account.balance() - amount;
        if (newBalance < -limit)
        {
            throw new RuntimeException("Overdraft limit (of " + limit + ") exceeded: " + newBalance);
        }
    }

    public void postDebit(Account account, double amount)
    {
    }
}
