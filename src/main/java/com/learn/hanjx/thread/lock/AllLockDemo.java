package com.learn.hanjx.thread.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 各种类型单机锁使用 demo
 */
public class AllLockDemo {
	
	Integer i = 1;
	/*
	 * 同步关键字 轻量级
	 */
	private  void sync(){
		synchronized(i){			
			i= i+1;
		}
	}
	/*
	 * lock 锁  重量级
	 * api比较多 比较灵活
	 */
	private Lock lock = new ReentrantLock();
	private  void lock(){
		try {
			if(lock.tryLock()){
				lock.lock();
				i= i+1;
				if(i<0){
					lock.lockInterruptibly();
				}
			}
		} catch (InterruptedException e) {
			//the current thread's interrupted status is cleared.
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	/*
	 * volatile
	 */
	volatile Integer i3 =1 ;
	volatile Map<String,String> map3 = new HashMap();
	private  void vola(){
		i3++;
		map3.put("", "");
	}
	/* 普通加锁：以时间换空间
	 * 
	 * ThreadLocalVariable 线程局部变量
	 * 生成很多变量副本，不同线程同时操作，以空间换时间
	 */
	ThreadLocal<Map<String,String>> map4 = new ThreadLocal<>();
	private  void threadLocal(){
		map4.get().put("key", "value");
	}
	/*
	 * AtomicInteger
	 * ...HashTable Vector 等特定的同步类
	 */
	AtomicInteger i5 = new AtomicInteger(1);
	private  void atomic(){
		i5.addAndGet(1);
	}
	

}
