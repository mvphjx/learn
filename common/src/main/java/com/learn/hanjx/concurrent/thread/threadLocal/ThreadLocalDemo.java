package com.learn.hanjx.concurrent.thread.threadLocal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * ThreadLocal，即线程变量，是一个以ThreadLocal对象为键、任意对象为值的存储结构
 * ThreadLocal的使用
 *
 */
public class ThreadLocalDemo implements Runnable
{
    private ThreadLocal<List<String>> local = ThreadLocal.withInitial(() -> {
        //初始化，设置值
        //以空间换时间，每个线程会建立数据备份
        return new ArrayList<>();
    });
    private AtomicInteger i = new AtomicInteger(0);

    @Override
    public void run()
    {
        //获取当前线程的数据
        List list = local.get();
        i.addAndGet(1);
        list.add(i.toString());
        System.out.println(Thread.currentThread() + Arrays.toString(list.toArray()));
    }

    /*
     * ThreadLocal的使用
     */
    public static void main(String[] args)
    {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        ThreadLocalDemo demo = new ThreadLocalDemo();
        for (int i = 0; i < 10; i++)
        {
            pool.submit(demo);
        }
        pool.shutdown();
    }
}
