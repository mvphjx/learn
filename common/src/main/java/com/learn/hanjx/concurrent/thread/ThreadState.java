package com.learn.hanjx.concurrent.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程状态
 * <p>
 * {@link Thread.State}
 * NEW：一个线程新new出来，但是还未start()的状态。
 * RUNNABLE：一个线程调用了start()之后的状态。
 * BLOCKED：阻塞状态，表示线程阻塞与锁。
 * WAITING：等待状态，需要等待其他线程通知或中断。
 * TIMED_WAITING：超时等待状态。
 * TERMINATED：一个线程运行完run()方法的状态。
 * <p>
 * 使用jvisualvm.exe查看线程状态。
 */
public class ThreadState
{
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args)
    {
        new Thread(new TimeWaiting(), "TimeWaitingThread").start();
        new Thread(new Waiting(), "WaitingThread").start();
        new Thread(new Blocked(), "BlockedThread1").start();
        new Thread(new Blocked(), "BlockedThread2").start();
        new Thread(new Sync(), "SyncThread-1").start();
        new Thread(new Sync(), "SyncThread-2").start();
    }

    static class TimeWaiting implements Runnable
    {

        @Override
        public void run()
        {
            while (true)
            {
                sleep(100);
            }
        }
    }

    static class Waiting implements Runnable
    {

        @Override
        public void run()
        {
            while (true)
            {
                synchronized (Waiting.class)
                {
                    try
                    {
                        Waiting.class.wait();
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    static class Blocked implements Runnable
    {

        @Override
        public void run()
        {
            synchronized (Blocked.class)
            {
                while (true)
                {
                    sleep(100);
                }
            }
        }
    }

    static class Sync implements Runnable
    {

        @Override
        public void run()
        {
            lock.lock();
            try
            {
                sleep(100);
            } finally
            {
                lock.unlock();
            }

        }

    }

    public static void sleep(int second)
    {
        try
        {
            //Thread.sleep(second * 1000);
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

}
