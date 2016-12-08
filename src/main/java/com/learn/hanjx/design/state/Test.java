package com.learn.hanjx.design.state;

/**
State（状态）
作用：将主对象和其状态分离，状态对象负责主对象的状态转换，使主对象类功能减轻
JDK中体现：未发现
 */
public class Test {

	public static void main(String[] args) {
		
		State state = new State();
		Context context = new Context(state);
		
		//设置第一种状态
		state.setValue("state1");
		context.method();
		
		//设置第二种状态
		state.setValue("state2");
		context.method();
	}
}
