package com.learn.hanjx.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
两个线程交叉打印100个0和100个1,1秒十次 
 */
public class MyThread1 implements Runnable{
	private int i=0; 
    public synchronized void run() {
    	for(int gg=0;gg<10;gg++){
        	System.out.print(i);
        	try {
    			this.wait(100);
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    }
}
