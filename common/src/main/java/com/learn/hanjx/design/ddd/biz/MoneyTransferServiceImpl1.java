package com.learn.hanjx.design.ddd.biz;

import com.learn.hanjx.design.ddd.Account;
import com.learn.hanjx.design.ddd.MoneyTransferService;

/**
 * 面向过程
 *
 在事务脚本的实现中，关于在两个账号之间转账的领域业务逻辑都被写在了MoneyTransferService的实现里面了，
 而Account仅仅是getters和setters的数据结构，也就是我们说的贫血模型
 */
public class MoneyTransferServiceImpl1 implements MoneyTransferService
{

    public static final String NEVER = "1";
    public static final String ALLOWED = "2";

    @Override
    public Object transfer(String fromAccountId, String toAccountId, double amount)
    {

        Account fromAccount = new Account();
        Account toAccount = new Account();
        double limit = 5.0;
        double newBalance = fromAccount.getBalance() - amount;
        switch (fromAccount.getOverdraftCode())
        {
        case NEVER:
            if (newBalance < 0)
            {
                throw new RuntimeException("Insufficient funds");
            }
            break;
        case ALLOWED:
            if (newBalance < -limit)
            {
                throw new RuntimeException("Overdraft limit (of " + limit + ") exceeded: " + newBalance);
            }
            break;
        }
        fromAccount.setBalance(newBalance);
        toAccount.setBalance(toAccount.getBalance() + amount);
        //fromAccountId, toAccountId, amount
        Object moneyTransferTransaction = new Object();
        return moneyTransferTransaction;
    }
}
