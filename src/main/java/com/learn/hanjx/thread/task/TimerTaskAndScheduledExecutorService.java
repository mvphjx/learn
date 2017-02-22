package com.learn.hanjx.thread.task;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import org.junit.Test;
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
	@Test
	public void ScheduledExecutorServiceTest() {
		ScheduledExecutorService se = new ScheduledThreadPoolExecutor(10);
		Runnable command =null;
		se.execute(command);
	}
}
