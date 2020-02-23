package com.learn.hanjx.jvm.out;
/*
 * http://blog.csdn.net/mvphjx/article/details/50723562
 * 测试
 * 内存溢出
 * 栈内存堆内存
 *
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo
{
	public static  void  main(String [] args) throws Exception{
		//getNumChange("0001");
		getOutOfThread();
	}
	/**
	 * 一直创建线程
	 * 测试  java.lang.OutOfMemoryError: Java heap space
	 * 堆内存溢出
	 * -Xmx10m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=E:\Java\dump
	 */
	private static void getOutOfThread(){
		ExecutorService pool = Executors.newCachedThreadPool();
		//创建实现了Runnable接口对象
		Runnable run  = ()->{
			System.out.println(Thread.currentThread().getName());
			try
			{
				Thread.sleep(100000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"  finish");
		};
		for(int i=1;i<=Integer.MAX_VALUE;i++){
			//将线程对象放入池中进行执行，也就是五个线程并发操作
			pool.submit(run);
		}
		//关闭线程池
		pool.shutdown();
	}

}
