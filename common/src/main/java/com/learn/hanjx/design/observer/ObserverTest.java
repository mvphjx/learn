package com.learn.hanjx.design.observer;

/**
Observer（观察者）
作用：通知对象状态改变
JDK中体现：
（1）java.util.Observer,Observable
（2）Swing中的Listener
 */
public class ObserverTest {

	public static void main(String[] args) {
		Subject sub = new MySubject();
		sub.add(new Observer1());
		sub.add(new Observer2());
		
		sub.operation();
	}

}
