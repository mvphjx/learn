package com.learn.hanjx.thread.synchronizedLock.sync;

/**
 *  实现线程同步
 *  synchronized 关键字   对象锁形式
 *    即  synchronized用来修饰 对象 或者  非静态成员方法
 *
 */
public class SyncObjectThread implements Runnable
{
    public int count = 0;

    @Override
    public synchronized void run()
    {
        for (int i = 0; i < 10000; i++)
        {
            count++;// count++ 不具备原子性，线程不安全
        }

    }

    public static void main(String[] args) throws InterruptedException
    {
        SyncObjectThread noSyncThread = new SyncObjectThread();
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
