package com.learn.hanjx.concurrent.thread.threadLocal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/*
 * ThreadLocal的实现原理
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
}
