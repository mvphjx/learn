package com.learn.hanjx.concurrent.thread.daemon;

import java.util.Timer;
import java.util.TimerTask;


/**
 * 线程的调度(守护线程)
 * 两个线程交叉打印100个0和100个1,1秒十次
 * 利用定时器实现
 */
public class ThreadTimerTask {
    public static void main(String[] args) {
    	//TODO  why  final
        final ThreadTimerTask thread=new ThreadTimerTask();
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
        	int count=0 ;
        	private  Long time= System.currentTimeMillis();
            public void run() {
                System.out.println("\n执行时间："+(System.currentTimeMillis()-time));
                Thread t2 = new Thread(thread.new MyRunnable0());
                Thread t3 = new Thread(thread.new MyRunnable1());
                //start方式启动线程
                   t2.start();
                   t3.start();
                   count++;
                   if (count == 10) {
                       System.out.println("定时器停止了");
                       timer.cancel();// 停止定时器
                   }
            }
        }, 1000, 1000);
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
