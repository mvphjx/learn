package com.learn.hanjx.thread.unitTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

import com.learn.hanjx.thread.test.CallAbleExample;

public class CallAbleExampleTest {  
  
    @Test
    /*
     * 通过  Future.get()  等待线程结束，同步返回结果
     */
    public  void test() throws InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newFixedThreadPool(2); 
		Callable demo = new CallAbleExample(1);
		List<Future<String>> list  = new  ArrayList<>();
		// 创建10个任务并执行  
        for (int i = 0; i < 10; i++) {  
        	Future<String> f= pool.submit(new CallAbleExample(i));   
        	list.add(f);
        }
        for(Future<String> f:list){
        	System.out.println(f.get());        	
        }
        System.out.println("[First]");
	}
      
}   

