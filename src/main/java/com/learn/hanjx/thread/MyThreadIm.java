package com.learn.hanjx.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 不加同步关键字，多个线程操作ticket
 * 会出现同步问题
 */
public class MyThreadIm implements Runnable{
	private int ticket=10;  
    public synchronized void run() {
    //public  void run() {
    		//模拟卖票
    		while(ticket>0){
    			System.out.println(Thread.currentThread().getName() + "正在执行卖票："+ticket+"号票");
    			ticket--;
    		}
    }
	public static void main(String[] args) {
        //创建一个可重用固定线程数的线程池
        ExecutorService pool = Executors.newFixedThreadPool(5);
        //创建实现了Runnable接口对象
        Runnable r=new MyThreadIm(); 
        for(int i=1;i<=5;i++){
        	//将线程对象放入池中进行执行，也就是五个线程并发操作
        	pool.submit(r);
        }
        //关闭线程池
        pool.shutdown();
    }

}
