package com.learn.hanjx.thread.sync;


public class SyncDemo implements Runnable{

	@Override
	public void run() {
		synchronized(this.getClass()){
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread());
			}			
		}

		
	}
	public static void main(String[] args) {
		SyncDemo sd = new SyncDemo();
		Thread t1 = new Thread(sd,"thread1");
		Thread t2 = new Thread(sd,"thread2");
		t1.start();
		t2.start();
	}

}
