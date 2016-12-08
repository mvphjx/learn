package com.learn.hanjx.design.mediator;

/**
Mediator（协调者，中介者）
作用：用于协调多个类的操作
JDK中体现：Swing的ButtonGroup

优点
简化了对象之间的交互
将各同事对象解耦
可以减少子类生成
 */
public class Test {

	public static void main(String[] args) {
		Mediator mediator = new MyMediator();
		mediator.createMediator();
		mediator.workAll();
	}
}
