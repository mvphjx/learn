package com.learn.hanjx.thread.synchronizedLock.util;

import java.util.concurrent.atomic.AtomicInteger;
/**
 * 原子、原子更新类
 * {@link java.util.concurrent.atomic}
 */
public class AtomicIntegerDemo implements Runnable
{
    private static AtomicInteger count = new AtomicInteger(0);

    @Override
    public synchronized void run()
    {
        count.getAndIncrement();
    }
}
