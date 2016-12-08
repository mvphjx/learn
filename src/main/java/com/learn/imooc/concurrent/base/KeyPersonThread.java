package com.learn.imooc.concurrent.base;

public class KeyPersonThread extends Thread {
	public void run (){
		System.out.println(Thread.currentThread().getName()+"开始战斗了");
		for(int i=0 ;i<10;i++){
			System.out.println(Thread.currentThread().getName()+" 关键先生攻击 【"+i+"】");
		}
		System.out.println(Thread.currentThread().getName()+" 战斗结束");
		
	}

}
