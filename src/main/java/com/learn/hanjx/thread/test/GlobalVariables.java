package com.learn.hanjx.thread.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GlobalVariables extends Thread{
	private boolean keeprunning = true;
	public void run (){
		System.out.println("keeprunning:"+keeprunning);
		System.out.println("start");
		int i =0;
		Lock lock = new ReentrantLock();
		//lock.lock();
		while(keeprunning){
			//执行println时候会到这段同步代码块，这样造成线程会强制去重新从主内存取值,会停止
			//System.out.println("running");
			
			//加锁时子线程的工作内存的数据会重新从主内存获取,会停止
			//synchronized(""){};
			
			//不会重新获取,不会停止
			//i++;
			
			//加锁时子线程的工作内存的数据会重新从主内存获取,会停止
			//lock.lock();
			
			//会停止,但有时会报异常IllegalMonitorStateException
			//if(lock.tryLock()){
			//	lock.unlock();
			//}
			
				
		}
		System.out.println("end");
	}
	public boolean isKeeprunning() {
		return keeprunning;
	}
	public void setKeeprunning(boolean keeprunning) {
		this.keeprunning = keeprunning;
	}
	public static void main(String[] args) {
		GlobalVariables t = new GlobalVariables();
		t.start();	
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t.setKeeprunning(false);
	}
	

}
