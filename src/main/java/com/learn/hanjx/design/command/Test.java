package com.learn.hanjx.design.command;


/**
Command（命令）
作用：
（1）封装操作，使接口一致
（2）将调用者和接收者在空间和时间上解耦合
JDK中体现：Runnable；Callable；ThreadPoolExecutor
 */
public class Test {

	public static void main(String[] args) {
		Receiver receiver = new Receiver();
		Command cmd = new MyCommand(receiver);
		Invoker invoker = new Invoker(cmd);
		invoker.action();
	}
}
