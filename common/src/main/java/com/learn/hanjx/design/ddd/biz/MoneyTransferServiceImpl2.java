package com.learn.hanjx.design.ddd.biz;

import com.learn.hanjx.design.ddd.Account;
import com.learn.hanjx.design.ddd.MoneyTransferService;

/**
 * DDD强调业务抽象和面向对象编程，而不是过程式业务逻辑实现
 *
 *
 * 使用 透支策略OverdraftPolicy  替代 枚举overdraftCode
 */
public class MoneyTransferServiceImpl2 implements MoneyTransferService
{
    @Override
    public Object transfer(String fromAccountId, String toAccountId, double amount)
    {
        Account fromAccount = new Account();
        Account toAccount = new Account();
        //业务逻辑  转入
        fromAccount.debit(amount);
        //业务逻辑 转出
        toAccount.credit(amount);
        //fromAccountId, toAccountId, amount
        Object moneyTransferTransaction = new Object();
        return moneyTransferTransaction;
    }
}
