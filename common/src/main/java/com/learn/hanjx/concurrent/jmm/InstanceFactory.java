package com.learn.hanjx.concurrent.jmm;

/**
 * Java多线程程序中，有时候需要采用延迟初始化来降低初始化类和创建对象的开销。
 * <p>
 * JVM在类的初始化阶段（即在Class被加载后，且被线程使用之前），会执行类的初始化。
 * 在执行类的初始化期间，JVM会去获取一个锁。这个锁可以同步多个线程对同一个类的初始化。
 * <p>
 * 下面是，基于类初始化的解决方案。
 */
public class InstanceFactory
{
    private static class InstanceHolder
    {
        public static Object instance = new Object();
    }


    public static Object getInstance()
    {

        return InstanceHolder.instance;

    }
}
