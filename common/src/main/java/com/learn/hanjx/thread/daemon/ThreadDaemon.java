package com.learn.hanjx.thread.daemon;


/**
 * 线程的调度(守护线程)
 * 两个线程交叉打印100个0和100个1,1秒十次 
 * 利用守护线程
 */
public class ThreadDaemon {
    private static Long time= 0L;
    private static Long timeplus= 0L;
    public static void main(String[] args) {
        ThreadDaemon thread=new ThreadDaemon();
        Thread t1=thread.new MyThread1();
        int i=0 ;
    	time=System.currentTimeMillis();
        while(true){
        	for(;i<10&&!t1.isAlive();i++){
        	   timeplus=System.currentTimeMillis();
           	   System.out.println("\nstart"+i);
               Thread t2 = new Thread(thread.new MyRunnable0()); 
               t2.setDaemon(true); //设置为守护线程   
               Thread t3 = new Thread(thread.new MyRunnable1()); 
               t3.setDaemon(true); //设置为守护线程
               t1 = thread.new MyThread1();
               //start方式启动线程
                  t2.start();
                  t3.start();
                  t1.start();
           	}
        	if(i>=10){
        		break;
        	}
        }


    }
    
    class MyThread1 extends Thread {
        public void run() {
        	//轮询，是不是已经1000MS了
        	while(System.currentTimeMillis()-timeplus<999){
        		try {
        			synchronized(this){  
        			if(this.isAlive()){
        				/*
        				 * The thread does not lose ownership of any monitors.
        				 *	不能释放时间片资源
        				 */
        				this.sleep(1L);
        				//this.wait(1L);
        			}
        		}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
            System.out.println("\nend:"+(System.currentTimeMillis()-time));    
            }  
    }

    class MyRunnable0 implements Runnable {
        public void run() {
            for (int i = 0; i < 10; i++) { 
                System.out.print(0); 
        } 
        }
    }
    class MyRunnable1 implements Runnable {
        public void run() {
            for (int i = 0; i < 10; i++) { 
                System.out.print(1); 
        } 
        }
    }
}
