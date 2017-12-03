package com.webMagic.service;

import us.codecraft.webmagic.Spider;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadTest implements Runnable{
	 public static int all = 3520;
	 public static AtomicInteger  page = new AtomicInteger(all);
	 public static void main(String[] args) throws InterruptedException {     
	        ThreadPoolExecutor executor = new ThreadPoolExecutor(50, 100, 500, TimeUnit.MILLISECONDS,  
	                new ArrayBlockingQueue<Runnable>(5)); 
	        for(int i=0;i<100;i++){ 
	        	ThreadTest myTask = new ThreadTest(); 
	            executor.execute(myTask); 
//	            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+ 
//	            executor.getQueue().size()+"，已执行完的任务数目："+executor.getCompletedTaskCount()); 
	        }
	        executor.shutdown(); 
	             
	    }

	@Override
	public void run() {
    	for(;;){
    		String url =GithubRepoPageProcessor.siteurl+ "/v.php?next=watch&page=";
//    		synchronized (page) {
//    			if(page==1){
//    				break;
//    			}
//    			page--;
//    			url=url+page;
//			}
    		if(page.intValue()==1){
				break;
			}
    		url=url+page.addAndGet(-1);
    		System.out.println("-get page: "+url);
	    	Spider s =Spider.create(new GithubRepoPageProcessor());
	        s.addUrl(url);   
	        s.thread(5);
	        s.run();
    	}
		
	}  
}
