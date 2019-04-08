#多线程总结

##线程与线程池

经典线程池
ThreadPoolExecutor
继承关系
Executor》ExecutorService》AbstractExecutorService》ThreadPoolExecutor

主要成员属性
BlockingQueue<Runnable>
ReentrantLock
HashSet<Worker>
Condition

volatiles 》》
ThreadFactory
RejectedExecutionHandler

##线程同步

###synchronized关键字
    
    本质是Lock的简单实现，加锁 释放锁时机不够灵活；但是代码量少；
    实现内存（共享变量）可见性 与 原子性
    
    可见性：一个线程对共享变量值的修改，能够及实地被其他线程看到。
    共享变量：如果一个变量在多个线程的工作内存中都存在副本，那么这个变量就是这几个线程的共享变量。所有的变量都存储在主内存中。
    线程的工作内存：每个线程都有自己独立的工作内存，里面保存该线程使用到的变量的副本(主内存中该变量的一份拷贝)。

###Lock接口

    强大而复杂的同步锁操作

###JUC工具类

    Semaphore 信号量
    CountDownLatch 一种非常简单、但很常用的同步辅助类
    CyclicBarrier 一种可重置的多路同步点，在某些并发编程场景很有用
    Phaser一种可重用的同步屏障，功能上类似于CyclicBarrier和CountDownLatch，但使用上更为灵活。
    Exchanger允许两个线程在某个汇合点交换对象，在某些管道设计时比较有用

    

