package com.learn.hanjx.concurrent.thread;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * 线程之间等待与通知
 *
 *
 * 等待/通知的经典范式
 *
 * synchronized(对象){
 *     while(条件判断){
 *         wait()
 *     }
 * }
 * synchronized(对象){
 *      改变条件
 *      notifyAll()
 * }
 *
 */
public class WaitNotify
{
    static boolean flag = true;
    static Object lock = new Object();
    private static DateTimeFormatter format = DateTimeFormatter.ofPattern("HH时mm分ss秒");

    public static void main(String[] args) throws InterruptedException
    {
        Thread threadWait = new Thread(new Wait(),"threadWait");
        threadWait.start();
        TimeUnit.SECONDS.sleep(1);
        Thread threadNotify = new Thread(new Notify(),"threadNotify");
        threadNotify.start();

    }

    static class Wait implements Runnable
    {

        @Override
        public void run()
        {
            synchronized (lock)
            {
                while (flag)
                {
                    try
                    {
                        System.out.println(Thread.currentThread() + "flag is true,wait@" + format.format(LocalDateTime.now()));
                        //1.放弃了锁并进入了对象的等待队列WaitQueue中，进入等待状态
                        lock.wait();
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                //2.调用notify()方法后，将WaitThread从WaitQueue移到SynchronizedQueue中，此时WaitThread的状态变为阻塞状态
                //3.NotifyThread释放了锁之后，WaitThread再次获取到锁并从wait()方法返回继续执行
                System.out.println(Thread.currentThread() + "flag is false,running@" + format.format(LocalDateTime.now()));
            }
        }
    }

    static class Notify implements Runnable
    {

        @Override
        public void run()
        {
            synchronized (lock)
            {
                System.out.println(Thread.currentThread() + "hold lock,notify@" + format.format(LocalDateTime.now()));
                //获取lock的锁，然后进行通知，通知时不会释放lock的锁
                //直到当前线程释放了lock后，waitThread才能从wait方法中返回
                lock.notifyAll();
                flag = false;
                ThreadState.sleep(5);
            }
            /**
             * 与waitThread竞争后，再次获取锁
             */
            synchronized (lock)
            {
                System.out.println(Thread.currentThread() + "hold lock,sleep@" + format.format(LocalDateTime.now()));
                ThreadState.sleep(5);
            }
        }
    }

}
