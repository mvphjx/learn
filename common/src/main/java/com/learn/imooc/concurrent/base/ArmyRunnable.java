package com.learn.imooc.concurrent.base;
/**
 * 军队线程
 * @author han
 *
 */
public class ArmyRunnable extends Thread {
	//volatitle为了保证线程可以正确的读取其他线程的写入值。
	//可见性 ref  jmm，happen-before原则
	volatile boolean keeprunning = true;
	public void run (){
	   // five hit
	   while(keeprunning){
		   for(int i=0;i<5;i++){
			   System.out.println(Thread.currentThread().getName()+" 发动攻击"+"["+i+"]");
			   Thread.yield();
		   }
	   }
   }
   
}
