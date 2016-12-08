package com.learn.Try.T2016.T03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 不加同步
 */
public class Runnable_demo0 implements Runnable{  
    private int ticket=10;  
    public Runnable_demo0(){       
    }  
    @Override  
    public void run() {  
        for(int i=0;i<20;i++){  
                if(this.ticket>0){  
                    //休眠1s秒中，为了使效果更明显，否则可能出不了效果  
                    try {  
                        Thread.sleep(1000);  
                    } catch (Exception e) {  
                        e.printStackTrace();  
                    }  
                    System.out.println(Thread.currentThread().getName()+"号窗口卖出："+this.ticket--+"号票");  
                }  
              
        }  
    }  
      
     public static void main(String args[]){  
         Runnable_demo0 demo=new Runnable_demo0();  
         //基于火车票创建三个窗口  
         new Thread(demo,"a").start();  
         new Thread(demo,"b").start();  
         new Thread(demo,"c").start();  
     }  
      
}   