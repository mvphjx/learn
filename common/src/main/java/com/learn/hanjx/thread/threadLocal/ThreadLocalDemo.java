package com.learn.hanjx.thread.threadLocal;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
/*
 * ThreadLocal的使用与实现原理
 * 
 */
public class ThreadLocalDemo implements Runnable{
	private ThreadLocal<Map<String,String>> local = new  ThreadLocal<Map<String,String>>();
	private AtomicInteger i = new AtomicInteger(0);
	{
		/*初始化
		 *以空间换时间，每个线程会建立数据备份。
		 */
		local.set(new HashMap<String,String>());
	}	
	@Override
	public void run() {
		i.addAndGet(1);
		if(local.get()==null){
			local.set(new HashMap<String,String>());
		}
		Map<String,String> map=local.get();
		map.put(i.toString(), "");
		String s = "";
		Set<Entry<String, String>>  set = map.entrySet();
		for(Entry<String, String> e :set){
			s = s +"/"+ e.getKey();
		}
		System.out.println(Thread.currentThread()+"----"+s);
	}
	
	/*
	 * ThreadLocal的使用
	 */
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(3); 
		ThreadLocalDemo demo = new ThreadLocalDemo();
		for (int i=0;i<10;i++){
			pool.submit(demo);
			//pool.execute(demo);
		}
		pool.shutdown();
	}
}
