package com.learn.hanjx.concurrent._volatile;

/**
 * tip：内存可见性  Volatile
 * <p>
 * 如果isRunning 没有设置内存可见性
 * main线程中已经设置isRunning为false，
 * 子线程thread  while (isRunning)却读取不到，没有停止线程.
 * <p>
 * 解决方案
 * 增加修饰词  Volatile 实现可见性；
 * 限制编译器重排序、处理器重排序
 */
public class VisibilityBoolDemo implements Runnable
{
    //volatile
    private volatile boolean isRunning = true;

    public boolean isRunning()
    {
        return isRunning;
    }

    public void setRunning(boolean isRunning)
    {
        this.isRunning = isRunning;
    }


    @Override
    public void run()
    {
        System.out.println("进入run了");
        while (isRunning)
        {

        }
        System.out.println("线程被停止了！");
    }

    @Override
    public String toString()
    {
        return "NoVisibility{" +
                "isRunning=" + isRunning +
                '}';
    }

    public static void main(String[] args) throws InterruptedException
    {
        VisibilityBoolDemo thread = new VisibilityBoolDemo();
        new Thread(thread).start();
        Thread.sleep(100);
        thread.setRunning(false);
        System.out.println("Main:" + thread);
    }
}
