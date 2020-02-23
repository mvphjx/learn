package com.learn.hanjx.concurrent.jmm;

/**
 * Java多线程程序中，有时候需要采用延迟初始化来降低初始化类和创建对象的开销。
 * 双重检查锁定是常见的延迟初始化技术。
 * 双重检查锁定（Double-Checked Locking）
 */
public class DoubleCheckedLocking
{
    /**
     * volatile 可以避免创建对象工程中的重排序
     */
    private volatile static Object instance;

    public static Object getInstance()
    {
        if (instance == null)//第一次检查
        {
            synchronized (Object.class)//加锁
            {
                if (instance == null)//第二次检查
                {
                    /**
                     * 1分配对象的内存
                     * 2初始化对象
                     * 3设置instance指向刚分配的内存地址
                     */
                    instance = new Object();
                }
            }
        }
        return instance;

    }
}
