package com.learn.imooc.concurrent;

public class Actor extends Thread {

	public void run(){
		int count =0;
		boolean keeprunning = true;
		while(keeprunning){
			System.out.println(getName()+" start");
			System.out.println(getName()+" show "+(++count));
			System.out.println(getName()+" end");
			if(count>=100){
				keeprunning= false;
			}
			if(count%10==0){
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	public static void main(String [] a){
		Thread t1 = new Actor();
		t1.setName("actor");
		t1.start();
		Thread t2 = new Actor();
		t2.setName("Runnable");
		t2.start();
		//不要使用 stop,突然结束不安全； 使用退出标示
		//t2.stop();
	}
}
