package com.learn.hanjx.thread.task;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * 验证定时器   ScheduledExecutorService  优于   Timer
 * 
 * TODO
 * timer只要TimerTask抛出异常，所有的  TimerTask会停止执行
  * @author han
 *
 */
public class TimerTaskAndScheduledExecutorService {
	@Test
	public void TimeTaskTest() throws InterruptedException{
		final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
        	int count=0 ;
        	private  Long time= System.currentTimeMillis();
            public void run() {
                System.out.println("\n执行时间："+(System.currentTimeMillis()-time));
                   count++;
                   if (count == 10) {
                       System.out.println("定时器停止了");
                       timer.cancel();// 停止定时器
                   }
            }
        }, 1000, 1000);
	}
	// TODO  无法测试
	@Test
	public void ScheduledExecutorServiceTest() throws InterruptedException
    {
		ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(10);
		Runnable beeper = new Runnable() {
			public void run() { System.out.println("beep"); }
		};
		ScheduledFuture<?> beeperHandle =
				scheduler.scheduleAtFixedRate(beeper, 1, 2, SECONDS);
		scheduler.schedule(new Runnable() {
			public void run() { beeperHandle.cancel(true); }
		}, 10, SECONDS);
        scheduler.shutdown();
        scheduler.awaitTermination(1, TimeUnit.HOURS);
	}

	public static void main(String[] args) throws InterruptedException
    {
		ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(10);
		Runnable beeper = new Runnable() {
			public void run() { System.out.println("beep"); }
		};
		//每10秒执行一次 警报
		ScheduledFuture<?> beeperHandle =
				scheduler.scheduleAtFixedRate(beeper, 1, 2, SECONDS);
        //10秒后关闭
        scheduler.schedule(new Runnable() {
            public void run() { beeperHandle.cancel(true); }
        }, 10, SECONDS);
	}
}
