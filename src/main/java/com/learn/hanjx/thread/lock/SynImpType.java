package com.learn.hanjx.thread.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynImpType {
	
	Integer i = 1;
	/*
	 * 同步关键字 轻量级
	 */
	private  void dosomeThing1(){
		synchronized(i){			
			i= i+1;
		}
	}
	/*
	 * lock 锁  重量级
	 */
	private  void dosomeThing2(){
		Lock lock = new ReentrantLock();
		lock.lock();
		i= i+1;
		lock.unlock();
	}
	/*
	 * volatile
	 */
	volatile Integer i3 =1 ;
	volatile Map<String,String> map3 = new HashMap();
	private  void dosomeThing3(){
		i3++;
		map3.put("", "");
	}
	/* 普通加锁：以时间换空间
	 * 
	 * ThreadLocalVariable 线程局部变量
	 * 生成很多变量副本，不同线程同时操作，以空间换时间
	 */
	ThreadLocal<Map<String,String>> map4 = new ThreadLocal<>();
	private  void dosomeThing4(){
		map4.get().put("key", "value");
	}
	/*
	 * AtomicInteger
	 * ...HashTable Vector 等特定的同步类
	 */
	AtomicInteger i5 = new AtomicInteger(1);
	private  void dosomeThing5(){
		i5.addAndGet(1);
	}
	

}
