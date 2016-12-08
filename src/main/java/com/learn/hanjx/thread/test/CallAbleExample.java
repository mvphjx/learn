package com.learn.hanjx.thread.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallAbleExample implements Callable<String> {  
    private int id;  
    public CallAbleExample(int id) {  
        this.id = id;  
    }  
  
    /** 
     * 任务的具体过程，一旦任务传给ExecutorService的submit方法，则该方法自动在一个线程上执行。 
     *  
     * @return 
     * @throws Exception 
     */  
    public String call() throws Exception {  
        System.out.println("call()方法被自动调用,干活！！！             " + Thread.currentThread().getName());  
        if (new Random().nextBoolean())  
           // throw new TaskException("Meet error in task." + Thread.currentThread().getName());  
        // 一个模拟耗时的操作  
        Thread.sleep(3000);
        return "call()方法被自动调用，任务的结果是：" + id + "    " + Thread.currentThread().getName();  
    }  
    
    /*
     * 接收/处理 返回值
     * 回调操作
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newFixedThreadPool(2); 
		Callable demo = new CallAbleExample(1);
		List<Future<String>> list  = new  ArrayList<>();
		// 创建10个任务并执行  
        for (int i = 0; i < 10; i++) {  
        	Future<String> f= pool.submit(new CallAbleExample(i));   
        	list.add(f);
        }
        System.out.println(list.size());
        for(Future<String> f:list){
        	System.out.println(list.size());    
        	//Waits if necessary for the computation to complete, and then retrieves its result.
        	//1等待线程执行完毕  2获取值
        	System.out.println(f.get());  

        }
        System.out.println("[First]");
	}
}   
class TaskException extends Exception {  
    public TaskException(String message) {  
        super(message);  
    }  
}  
