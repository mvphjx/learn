package com.learn.hanjx.thread.sync;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程同步
 * synchronized 关键字
 * volatile
 */
public class SyncDemo implements Runnable
{

    @Override
    public void run()
    {
        synchronized (this.getClass())
        {
            for (int i = 0; i < 5; i++)
            {
                System.out.println(Thread.currentThread());
            }
        }


    }

    public static void main(String[] args)
    {
        SyncDemo sd = new SyncDemo();
        Thread t1 = new Thread(sd, "thread1");
        Thread t2 = new Thread(sd, "thread2");
        t1.start();
        t2.start();
    }
    public static void mainDemo(String[] args)
    {
        SampleSynchronized sampleSynchronized = new SampleSynchronized();
        Thread synchronized1 = new Thread(sampleSynchronized, "thread1");
        Thread synchronized2 = new Thread(sampleSynchronized, "thread2");
        synchronized1.start();
        synchronized2.start();
    }

}

/**
 * synchronized 关键字
 */
class SampleSynchronized implements Runnable
{
    private static int count = 0;

    @Override
    public synchronized void run()
    {
        count++;
    }
}
/**
 * lock 锁  重量级
 */
class SampleLock implements Runnable
{
    private static int count = 0;
    private Lock lock = new ReentrantLock();
    @Override
    public synchronized void run()
    {
        lock.lock();
        count++;
        lock.unlock();
    }
}
/**
 * 原子、原子更新类
 * {@link java.util.concurrent.atomic}
 */
class SampleAtomicInteger implements Runnable
{
    private static AtomicInteger count = new AtomicInteger(0);

    @Override
    public synchronized void run()
    {
        count.getAndIncrement();
    }
}

/**
 * volatile 关键字
 * 语义：易变的，不稳定的
 * 加锁机制既可以确保可见性又可以确保原子性，而volatile变量确保多线程时的可见性。
 */
class SampleVolatile implements Runnable
{
    private static volatile int count = 0;
    @Override
    public synchronized void run()
    {
        count++;
    }
}
/* 普通加锁：以时间换空间
 * ThreadLocalVariable 线程局部变量
 * 生成很多变量副本，不同线程同时操作，以空间换时间
 */
class SampleThreadLocal implements Runnable
{
    ThreadLocal<Integer> count = new ThreadLocal<>();
    @Override
    public synchronized void run()
    {
        int count1=count.get();
        count1++;
    }
}


