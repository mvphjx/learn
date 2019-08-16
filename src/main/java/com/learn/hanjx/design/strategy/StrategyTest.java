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
JDK中的体现：ThreadPoolExecutor中的四种拒绝策略

 使用场景： 一个系统需要动态地在几种算法中选择一种，而这些算法之间仅仅是他们的行为不同。 此外决策过程中过多的出现if else，也可以考虑使用该模式。
 实现：将这些算法封装成可单独运行的类，由使用者根据需要进行替换。
 优点： 较为灵活，扩展性好，避免大量的if else结构。
 缺点： 对外暴露了类所有的行为和算法，行为过多导致策略类膨胀。
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
		BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(10);
		/*
			策略模式处理对象如何执行特定任务 - 它封装了算法。
		 * JDK中的体现：ThreadPoolExecutor中的四种拒绝策略
		 *
		 */
		//拒绝策略1：将抛出 RejectedExecutionException.
		RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
		ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,maxPoolSize,keepAliveTime,TimeUnit.SECONDS,queue,handler);
		//创建一个可重用固定线程数的线程池
        ExecutorService pool = Executors.newFixedThreadPool(2);
	}
}
