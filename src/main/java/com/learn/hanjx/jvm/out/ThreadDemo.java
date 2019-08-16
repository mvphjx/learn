package com.learn.hanjx.jvm.out;
/*
 * http://blog.csdn.net/mvphjx/article/details/50723562
 * 测试
 * 内存溢出
 * 栈内存堆内存
 * 
 */

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadDemo
{
	public static  void  main(String [] args) throws Exception{
		getOutOfThread();
	}
	/**
	 * 一直创建线程
	 * 测试  java.lang.OutOfMemoryError: Java heap space
	 * 堆内存溢出
	 * -Xmx10m -Xss2m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=E:\Java\dump
	 */
	private static void getOutOfThread(){
		for(int i=1;i<=Integer.MAX_VALUE;i++){
			new Thread() {
				public void run() {
					String threadName = Thread.currentThread().getName();
					ArrayList<String> list = new ArrayList<>();
					list.add(threadName);
					list.add(threadName+"0");
					System.out.println(threadName);
					try {
						Thread.sleep(100000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
	}

}
