package com.learn.hanjx.concurrent.thread;

import java.util.concurrent.TimeUnit;

/**
 * 安全地终止线程
 */
public class ShutdownThread
{
    public static void main(String[] args) throws InterruptedException
    {
        Runner runner = new Runner();
        Thread thread = new Thread(runner);
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        //通过中断，终止线程
        thread.interrupt();
        Runner runner2 = new Runner();
        Thread thread2 = new Thread(runner2);
        thread2.start();
        TimeUnit.SECONDS.sleep(1);
        //通过 on标识，终止线程
        runner2.shutdown();

    }

    private static class Runner implements Runnable
    {

        private long i;
        private volatile boolean on = true;

        @Override
        public void run()
        {
            while (on && !Thread.currentThread().isInterrupted())
            {
                i++;
            }
            System.out.println("i:" + i);
        }

        public void shutdown()
        {
            on = false;
        }
    }
}
