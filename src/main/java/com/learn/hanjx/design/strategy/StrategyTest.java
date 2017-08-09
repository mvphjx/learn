package com.learn.hanjx.design.strategy;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
Strategy（策略）
作用：提供不同的算法
JDK中的体现：ThreadPoolExecutor中的四种拒绝策略
 */
public class StrategyTest {

	public static void main(String[] args) {
		String exp = "8+8";
		AbstractCalculator cal = new Plus();
		int result = cal.calculate(exp, "\\+");
		System.out.println(result);

	}
	public void test(){
		int corePoolSize = 5;
		int maxPoolSize = 10;
		long keepAliveTime = 5;
		BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(10);		 
		/*
		 * JDK中的体现：ThreadPoolExecutor中的四种拒绝策略
		 */
		//拒绝策略1：将抛出 RejectedExecutionException.
		RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
		ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,maxPoolSize,keepAliveTime,TimeUnit.SECONDS,queue,handler);
		//创建一个可重用固定线程数的线程池
        ExecutorService pool = Executors.newFixedThreadPool(2);
	}
}
