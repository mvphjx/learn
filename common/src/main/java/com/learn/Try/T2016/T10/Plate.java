package com.learn.Try.T2016.T10;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class Plate {
	List<Object> eggs = new ArrayList<Object>();
	
	public synchronized Object getEgg(){
		
		if(eggs.size()==0){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		Object egg = eggs.get(0);
		eggs.clear();//清空盘子  
		notify();//唤醒阻塞队列的某线程到就绪队列 
		System.out.println("取出一个鸡蛋");
		return egg;
	}
	
	public synchronized void putEgg(Object egg){
		if(eggs.size()>0){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		eggs.add(egg);//往盘子里放鸡蛋  
		System.out.println("放入一个鸡蛋");
		notify();///唤醒阻塞队列的某线程到就绪队列  
	}
	
	public static void main(String[] args) {
		final Plate p = new Plate();
		Thread a = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true)
					p.putEgg("egg");
			}
		});
		Thread b = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true)
					p.getEgg();
			}
		});
		a.start();
		b.start();
	}
}