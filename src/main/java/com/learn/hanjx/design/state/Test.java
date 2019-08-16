package com.learn.hanjx.design.state;

/**
State（状态）
作用：将主对象和其状态分离，状态对象负责主对象的状态转换，使主对象类功能减轻
JDK中体现：未发现

 使用场景： 对象依赖装填，行为随状态改变而改变的情景，或者存在大量的if else和分支结构等；
 实现：将对象的状态封装成单个的类，每个状态处理该状态下的事务，并控制该状态到其他状态的转移；
 优点： 容易新加状态，封装了状态转移规则，每个状态可以被复用和共享，避免大量的if else结构。
 缺点： 该模式结构和实现相对复杂，状态过多导致增加类和对象个数。同时由于由每个状态控制向其他状态的转移，新加状态必须要修改现有的部分状态才能加入状态机中生效。
 		状态转移 存在耦合度
 */
public class Test {

	public static void main(String[] args) {
		
		State state = new State();
		Context context = new Context(state);

		/**
		 * 	切换到"state1"状态
		 *  State模式处理对象所处的状态 - 它封装了依赖于状态的行为
		 */
		state.setValue("state1");
		context.method();
		
		//切换到"state2"状态
		state.setValue("state2");
		context.method();
	}
}
