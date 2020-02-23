package com.learn.hanjx.concurrent.thread;

/**
 * Daemon线程是一种支持型线程，因为它主要被用作程序中后台调度以及支持性工作
 * 这意味着，当一个Java虚拟机中不存在非Daemon线程的时候，Java虚拟机将会退出。
 */
public class DaemonThread
{
    public static void main(String[] args)
    {
        Thread daemonThread = new Thread(new DaemonRunner(), "DaemonThread");
        //Daemon属性需要在启动线程之前设置，不能在启动线程之后设置。
        daemonThread.setDaemon(true);
        daemonThread.start();
    }

    static class DaemonRunner implements Runnable
    {


        @Override
        public void run()
        {
            try
            {
                ThreadState.sleep(10);
            } finally
            {
                //在构建Daemon线程时，不能依靠finally块中的内容来确保执行关闭或清理资源的逻辑。
                System.out.println("DaemonThread finally run.");
            }

        }
    }
}
