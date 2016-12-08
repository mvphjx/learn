package com.learn.hanjx.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
两个线程交叉打印100个0和100个1,1秒十次 
利用
 sleep 一直在执行状态，大体满足
 wait  可能唤醒之后  不会立刻分到时间片
 */
public class MyThread2 implements Runnable{
	private int i=0; 
	private int j=1;  
    public synchronized void run() {
    	for(int gg=0;gg<10;gg++){
        	System.out.print(j);
        	try {
    			this.wait(100);
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}

    }
	public static void main(String[] args) throws InterruptedException {
        //创建一个可重用固定线程数的线程池
        ExecutorService pool = Executors.newFixedThreadPool(2);
        //创建实现了Runnable接口对象
        Runnable r=new MyThread2(); 
        Runnable r1=new MyThread1(); 
        for(int i=1;i<=10;i++){
        	System.out.println("\n第"+i+"次：");
        	pool.submit(r);
        	pool.submit(r1);
        	//Thread.sleep(1000);
        	
        }
        //关闭线程池
        pool.shutdown();
    }

}
