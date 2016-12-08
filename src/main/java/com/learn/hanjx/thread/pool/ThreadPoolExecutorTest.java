package com.learn.hanjx.thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest { 
    public static void main(String[] args) throws InterruptedException {     
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 500, TimeUnit.MILLISECONDS,  
                new ArrayBlockingQueue<Runnable>(5)); 
        Executors.newCachedThreadPool();
        Executors.newFixedThreadPool(10);
        for(int i=0;i<15;i++){ 
            MyTask myTask = new MyTask(i); 
            executor.execute(myTask); 
            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+ 
            executor.getQueue().size()+"，已执行完的任务数目："+executor.getCompletedTaskCount()); 
        }
        for(int i =0;i<20;i++){
			Thread.sleep(100);
            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+ 
                    executor.getQueue().size()+"，已执行完的任务数目："+executor.getCompletedTaskCount());         	
        }
        executor.shutdown(); 
             
    }  
}  
