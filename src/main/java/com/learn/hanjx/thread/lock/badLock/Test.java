package com.learn.hanjx.thread.lock.badLock;
/*
 * 测试死锁
 */
public class Test extends Thread{
	private String jinling="";
	private String food="";
    public  void run() {
    		if(true){
    			do{
    				System.out.println("开始吃："+Thread.currentThread().getName() +"");
    				synchronized(jinling){
    				if(jinling.isEmpty()){
                			try {
            					this.sleep(100);
            				} catch (InterruptedException e) {
            					e.printStackTrace();
            				}
                			jinling=Thread.currentThread().getName();
                			System.out.println("获取资源jinling："+jinling);
    					}
            		}
    				synchronized(food){
            		if(food.isEmpty()){
            			try {
        					this.sleep(100);
        				} catch (InterruptedException e) {
        					e.printStackTrace();
        				}
            			food=Thread.currentThread().getName();
            			System.out.println("获取资源food："+food);
            		}}
    			}while((Thread.currentThread().getName().equals(food)||Thread.currentThread().getName().equals(jinling)));
    		}

    	    System.out.println("吃完了："+Thread.currentThread().getName() +"");
    }
	public static void main(String[] args) {
		Thread t1=new Test();
		Thread t2=new Test();
		t1.start();
		t2.start();
    }

}