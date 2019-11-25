package com.learn.hanjx.thread.pool;
/*
 * 线程任务
 */
public class MyTask implements Runnable { 
	   private int taskNum; 
	     
	   public MyTask(int num) { 
	       this.taskNum = num; 
	   } 
	     
	   @Override
	   public void run() { 
	       //System.out.println("正在执行task "+taskNum); 
	       try { 
	           Thread.currentThread().sleep(500); 
	       } catch (InterruptedException e) { 
	           e.printStackTrace(); 
	       } 
	       //System.out.println("task "+taskNum+"执行完毕"); 
} 
}