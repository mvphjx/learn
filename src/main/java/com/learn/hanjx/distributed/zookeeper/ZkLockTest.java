package com.learn.hanjx.distributed.zookeeper;
/**
 * 测试一下我们的分布式共享锁 
 */
import org.junit.Test;

import com.learn.hanjx.distributed.zookeeper.ConcurrentTest.ConcurrentTask;


public class ZkLockTest {
	public static String URL = "127.0.0.1:2181";
	public static String URL2 = "127.0.0.1:2182";
	public static String URL3 = "127.0.0.1:2183";
	 @Test
	 public  void mainOK() throws InterruptedException {
		 DistributedLock lock = null;
		 lock = new DistributedLock(URL,"test1");
		 lock.lock();
         Thread.sleep(3000);
         System.out.println("===Thread " + Thread.currentThread().getId() + " running");  
	}
    public static void main(String[] args) {
    	Runnable task1 = new Runnable(){
			public void run() {
				DistributedLock lock = null;
				try {
					lock = new DistributedLock(URL,"test2");
					lock.lock();
					Thread.sleep(3000);
					System.out.println("===Thread " + Thread.currentThread().getId() + " running");
				} catch (Exception e) {
					e.printStackTrace();
				} 
				finally {
					if(lock != null)
						lock.unlock();
				}
				
			}
			
		};
		//new Thread(task1).start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		ConcurrentTask[] tasks = new ConcurrentTask[10];
		for(int i=0;i<tasks.length;i++){
			ConcurrentTask task3 = new ConcurrentTask(){
				public void run() {
					DistributedLock lock = null;
					try {
						lock = new DistributedLock(URL,"test2");
						lock.lock();
						System.out.println("Thread " + Thread.currentThread().getId() + " running");
					} catch (Exception e) {
						e.printStackTrace();
					} 
					finally {
						if(lock!=null)
							lock.unlock();
					}
					
				}
			};
			tasks[i] = task3;
		}
		new ConcurrentTest(tasks);
	}
}