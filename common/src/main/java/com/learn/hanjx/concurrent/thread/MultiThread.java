package com.learn.hanjx.concurrent.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 *
 *一个Java程序的运行不仅仅是main()方法的运行，而是main线程和多个其他线程的同时运行
 *
 * 6-Monitor Ctrl-Break
 * 5-Attach Listener
 * 4-Signal Dispatcher
 * 3-Finalizer
 * 2-Reference Handler
 * 1-main
 */
public class MultiThread
{
    public static void main(String[] args)
    {
        //获取线程管理
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        //不需要获取同步的monitor和synchronizer信息，仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos)
        {
            System.out.println(threadInfo.getThreadId() + "-" + threadInfo.getThreadName());
        }

    }
}
