package com.learn.hanjx.thread.unitTest;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * JUnit多线程测试，
 * 线程池可以通过shutdowm与awaitTermination配合实现
 * 线程可以通过join实现
 */
import org.junit.Test;

import com.learn.hanjx.thread.pool.MyTask;

public class JunitThread {
	{
		System.out.println("jvm可用线程："+Runtime.getRuntime().availableProcessors());
		System.out.println("->JUnit多线程测试");
		System.out.println("->线程池[ExecutorService]可以通过shutdowm与awaitTermination配合实现");
		System.out.println("->线程池[ExecutorService]也可以通过 callable 返回值（回调）实现");
		System.out.println("->线程[Thread]可以通过join实现");
		
	}
	//线程任务
	private MyTask task = new MyTask(0);
	/*
	 * 创建一个定长/可变线程池
	 */
	@Test
	public void testThread_OK() throws InterruptedException {
		Thread t = new Thread(task);
		t.start();
		//让测试线程  等待t执行完毕
		t.join();
	}
	/*
	 * 创建一个定长/可变线程池
	 */
	@Test
	public void testExecutors(){
		ExecutorService es = null;
		es = Executors.newCachedThreadPool(); 
		es = Executors.newFixedThreadPool(10); 
		es.submit(task);
		//关闭
		System.out.println("pool shutdowm");
		es.shutdown();
		//等待关闭
		try {
			es.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	/*
	 * 创建一个定长线程池，支持定时及周期性任务执行。
	 */
	@Test
	public void testScheduled() throws InterruptedException{
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		scheduledThreadPool.execute(task);
		scheduledThreadPool.schedule(task, 5, TimeUnit.SECONDS);
		scheduledThreadPool.shutdown();
		scheduledThreadPool.awaitTermination(1, TimeUnit.HOURS);
	}
	public static void main(String[] args) {
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		MyTask task = new MyTask(1);
		scheduledThreadPool.schedule(task, 5, TimeUnit.SECONDS);
	}
}
