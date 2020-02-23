package com.learn.hanjx.concurrent._volatile;


import org.apache.commons.lang.ArrayUtils;
/**
 * volatile 实现内存可见性,但是不一定保证原子性
 * 轻量级synchronized
 *      当前increase 没有保证++操作的原子性
 */
public class VolatileNumDemo
{

    private volatile int number = 0;

    public int getNumber()
    {
        return this.number;
    }

    public void increase()
    {
        try
        {
            Thread.sleep(100);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        this.number++;
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws InterruptedException
    {
        final VolatileNumDemo volDemo = new VolatileNumDemo();
        for (int i = 0; i < 10; i++)
        {
            new Thread(new Runnable()
            {

                @Override
                public void run()
                {
                    volDemo.increase();
                }
            }).start();
        }
        //如果还有子线程在运行，主线程就让出CPU资源，
        //直到所有的子线程都运行完了，主线程再继续往下执行
        while (Thread.activeCount() > 1)
        {
            Thread.sleep(100);
            //idea run模式 会有两个系统线程；debug模式 只有一个
            String[] systemThreadName = {"main", "Monitor Ctrl-Break"};
            boolean doBreak = true;
            Thread[] list = new Thread[Thread.activeCount()];
            int count = Thread.enumerate(list);
            for (int i = 0; i < count; i++)
            {
                if (!ArrayUtils.contains(systemThreadName, list[i].getName()))
                {
                    doBreak = false;
                }
            }
            if (doBreak)
            {
                break;
            }
            Thread.yield();
        }

        System.out.println("number : " + volDemo.getNumber());
    }

}
