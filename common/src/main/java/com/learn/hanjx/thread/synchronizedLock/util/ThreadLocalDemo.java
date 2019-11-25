package com.learn.hanjx.thread.synchronizedLock.util;

/* 普通加锁：以时间换空间
 * ThreadLocalVariable 线程局部变量
 * 生成很多变量副本，不同线程同时操作，以空间换时间
 */
public class ThreadLocalDemo implements Runnable
{
    ThreadLocal<Integer> count = new ThreadLocal<>();
    @Override
    public synchronized void run()
    {
        int count1=count.get();
        count1++;
    }
}
