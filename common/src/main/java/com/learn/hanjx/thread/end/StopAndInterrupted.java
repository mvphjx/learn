package com.learn.hanjx.thread.end;
/*
 * 中断（Interrupt）一个线程意味着在该线程完成任务之前停止其正在进行的一切，有效地中止其当前的操作。
 * 
 * 中断标示不可作为结束标示来用。
 * 
 * 至于stop 由于结束的太突然，不建议使用。
 */
public class StopAndInterrupted {
	public static void main(String[] args) {
		MyRunnable0 t0 = new MyRunnable0();
		t0.start();
		try {
			Thread.currentThread().sleep(1, 100);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		t0.interrupt();
		
		
		
		MyRunnable1 t1 = new MyRunnable1();
		t1.start();
		try {
			Thread.currentThread().sleep(1, 100);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		t1.interrupt();
	}

}
class MyRunnable0 extends Thread {

	@Override
	public void run() {
		while(!this.isInterrupted()){
			 System.out.println(0); 
		}
		
	}

}
class MyRunnable1 extends Thread {

	@Override
	public void run() {
		if(!this.isInterrupted()){
			try {
				sleep(10L);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			 System.out.println(0); 
		}
		
	}

}