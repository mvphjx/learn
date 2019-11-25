package com.learn.hanjx.distributed.common.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * 任务缓冲池
 */
public class MultiThreadProcessor {
	private ThreadPoolExecutor executor =null;
	private String name ;
	private List<RunLock> list =Collections.synchronizedList(new ArrayList<RunLock>());
	private boolean refinishi=true ;
    public MultiThreadProcessor(String name){
        executor = new ThreadPoolExecutor(2, 5, 30, TimeUnit.SECONDS,  
                new ArrayBlockingQueue<Runnable>(5)); 
        this.name=name;
    }
    /**
     * 执行线程任务 
     * @param command
     */
    private void execute(Runnable command) {
    	executor.execute(command);
    }  
    private void execute(List<RunLock> list) {
    	for(RunLock run:list){
    		this.execute(run);
    	}
    	refinishi=true;
    }
    /**
     * 加入任务缓冲池 线程组，等待执行
     * @param runLock
     */
	public void addProcessor(RunLock runLock) {
		refinishi=false;
		list.add(runLock);
		
	}
	/**
	 * 执行
	 */
	public void start() {
		this.execute(list);
		
	}  
	/**
	 * 关闭
	 *调用没有额外的效果，如果已经关闭。
	 *任务被执行，但没有新的任务将被接受,此方法不等待先前提交的任务完成执行
	 * @throws InterruptedException 
	 */
	public void close() throws InterruptedException {
		while(!refinishi){
			wait();
		}
		//会等待线程执行完毕，然后关闭线程池
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.DAYS);
	}
	
    public String getName() {
		return name;
	}
	@Override
    public String toString() {
        System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+ 
        executor.getQueue().size()+"，已执行完别的任务数目："+executor.getCompletedTaskCount());
		return ""; 
    }
}
