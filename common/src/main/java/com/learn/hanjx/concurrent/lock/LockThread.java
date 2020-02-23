package com.learn.hanjx.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 实现线程同步
 * 使用Lock  API实现,比较灵活，可以控制 中断响应、等待超时
 *
 * 中断响应  TODO
 */
public class LockThread implements Runnable
{
    public int count = 0;
    public Lock lock = new ReentrantLock();

    @Override
    public void run()
    {
        for (int i = 0; i < 10000; i++)
        {
            try
            {
                if (lock.tryLock(10, TimeUnit.SECONDS))
                {
                    try
                    {
                        count++;// count++ 不具备原子性，线程不安全
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    } finally
                    {
                        lock.unlock();
                    }
                } else
                {
                    System.out.println("等待超时，退出");
                }
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }


        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        LockThread noSyncThread = new LockThread();
        Thread thread1 = new Thread(noSyncThread);
        Thread thread2 = new Thread(noSyncThread);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("循环累加20000次之后，输出结果：");
        System.out.println(noSyncThread.count);
    }
}
