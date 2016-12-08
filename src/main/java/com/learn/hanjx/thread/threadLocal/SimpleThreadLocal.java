package com.learn.hanjx.thread.threadLocal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/*
 * ThreadLocal的使用与实现原理
 * 
 */
public class SimpleThreadLocal {
	//维护一个map，把变量和线程绑定
	private Map<Thread, Object> valueMap = Collections.synchronizedMap(new HashMap<Thread, Object>());
	public void set(Object newValue) {	
		//键为 线程对象，值为本线程的 变量副本		
		valueMap.put(Thread.currentThread(), newValue);
	}
	public Object get() {	
		Object o = valueMap.get(Thread.currentThread());
		return o;
	}
	public void remove() {
		valueMap.remove(Thread.currentThread());
	}
	public Object initialValue() {
		return null;
	}
	
	
	/*
	 * ThreadLocal的使用
	 */
	public static void main(String[] args) {
		//线程局部变量
		ThreadLocal<Map> local = new  ThreadLocal<Map>();
		//初始化
		if(local.get()==null){
			local.set(new HashMap<String,String >());			
		}
		//使用
		local.get().put("key", "value");
	}
}
