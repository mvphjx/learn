package com.learn.hanjx.thread.sync;

/**
 * Synchronized 实现内存（共享变量）可见性 与 原子性
 *
 *可见性：一个线程对共享变量值的修改，能够及实地被其他线程看到。
 *
 *共享变量：如果一个变量在多个线程的工作内存中都存在副本，那么这个变量就是这几个线程的共享变量。所有的变量都存储在主内存中。
 *
 *线程的工作内存：每个线程都有自己独立的工作内存，里面保存该线程使用到的变量的副本(主内存中该变量的一份拷贝)。
 *
 * http://blog.csdn.net/erlian1992/article/details/51712615#t1
 */
public class SynchronizedDemo {
	//共享变量
	private boolean ready = false;
	private int result = 0;
	private int number = 1;
	//写操作
	public void write(){
		ready = true;	      				 //1.1
		number = 2;		                    //1.2
	}
	//读操作
	public void read(){
		if(ready){						     //2.1
			result = number*3;	 	//2.2
		}
		System.out.println("result的值为：" + result);
	}

	//内部线程类
	private class ReadWriteThread extends Thread {
		//根据构造方法中传入的flag参数，确定线程执行读操作还是写操作
		private boolean flag;
		public ReadWriteThread(boolean flag){
			this.flag = flag;
		}
		@Override
		public void run() {
			if(flag){
				//构造方法中传入true，执行写操作
				write();
			}else{
				//构造方法中传入false，执行读操作
				read();
			}
		}
	}

	public static void main(String[] args)  {
		SynchronizedDemo synDemo = new SynchronizedDemo();
		//启动线程执行写操作
		synDemo .new ReadWriteThread(true).start();
		//启动线程执行读操作
		synDemo.new ReadWriteThread(false).start();
		//因为两个线程 执行顺序（指令重排序）不确定性 ， 结果为 6 或者 0
	}
}

