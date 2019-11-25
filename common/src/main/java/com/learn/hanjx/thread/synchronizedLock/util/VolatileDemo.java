package com.learn.hanjx.thread.synchronizedLock.util;
/**
 * Volatile 实现可见性
 */

import org.apache.commons.lang.ArrayUtils;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VolatileDemo
{

	private Lock lock = new ReentrantLock();
	private int number = 0;

	public int getNumber(){
		return this.number;
	}

	public void increase(){
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lock.lock();
		try {
			this.number++;
		} finally {
			lock.unlock();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException {
		final VolatileDemo volDemo = new VolatileDemo();
		for(int i = 0 ; i < 500 ; i++){
			new Thread(new Runnable() {

				@Override
				public void run() {
					volDemo.increase();
				}
			}).start();
		}
		//如果还有子线程在运行，主线程就让出CPU资源，
		//直到所有的子线程都运行完了，主线程再继续往下执行
		while(Thread.activeCount() > 1){
            Thread.sleep(100);
            //idea run模式 会有两个系统线程；debug模式 只有一个
            String[] systemThreadName = {"main", "Monitor Ctrl-Break"};
            boolean doBreak = true;
            Thread[] list = new Thread[Thread.activeCount()];
            int count = Thread.enumerate(list);
            for (int i = 0; i < count; i++) {
                if(!ArrayUtils.contains(systemThreadName,list[i].getName())){
                    doBreak=false;
                }
            }
            if(doBreak){
                break;
            }
			Thread.yield();
		}

		System.out.println("number : " + volDemo.getNumber());
	}

}
