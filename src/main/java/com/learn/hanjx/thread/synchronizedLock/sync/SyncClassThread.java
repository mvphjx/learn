package com.learn.hanjx.thread.synchronizedLock.sync;

/**
 *  实现线程同步
 *  synchronized关键字   类锁形式
 *      即  synchronized用来修饰 类 或者  静态方法
 */
public class SyncClassThread implements Runnable
{
    public int count = 0;

    @Override
    public void run()
    {
        synchronized(SyncClassThread.class){
            for (int i = 0; i < 10000; i++)
            {
                count++;// count++ 不具备原子性，线程不安全
            }
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        SyncClassThread noSyncThread = new SyncClassThread();
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
