package com.learn.hanjx.design.ddd;

public interface MoneyTransferService
{
    public Object transfer(String fromAccountId, String toAccountId, double amount);
}
