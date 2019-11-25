package com.learn.hanjx.thread.synchronizedLock.nosync;

/**
 * 一个线程不安全的例子
 */
public class NoSyncThread implements Runnable
{
    private volatile int count = 1;
    @Override
    public void run()
    {
        for (int i = 0; i < 10000; i++)
        {
            count++;// count++ 不具备原子性，线程不安全
        }

    }

    public static void main(String[] args) throws InterruptedException
    {
        NoSyncThread noSyncThread = new NoSyncThread();
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
