package com.learn.hanjx.concurrent.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * CAS(Compare And Swap),JVM中的CAS操作正是利用了处理器提供的CMPXCHG指令实现的
 * 在Java并发包中有这样一个包，java.util.concurrent.atomic，
 * 该包是对Java部分数据类型的原子封装，在原有数据类型的基础上，提供了原子性的操作方法，保证了线程安全。
 *
 * CAS虽然很高效地解决了原子操作，但是CAS仍然存在三大问题。
 * 1）ABA问题- {@link java.util.concurrent.atomic.AtomicStampedReference}
 * 2）循环时间长开销大
 * 3）只能保证一个共享变量的原子操作 {@link java.util.concurrent.atomic.AtomicReference}
 */
public class AtomicDemo
{

    public static class MyLock
    {
        private AtomicBoolean locked = new AtomicBoolean(false);

        public boolean lock()
        {
            return locked.compareAndSet(false, true);
        }
    }


}
