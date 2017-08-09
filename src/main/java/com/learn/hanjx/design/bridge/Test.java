package com.learn.hanjx.design.bridge;

/**桥接
 * 作用：将抽象部分与其实现部分分离，使它们都可以独立地变化
JDK中体现：java.util.logging中的Handler和Formatter
 */
public class Test {
	
	public static void main(String[] args) {
		
		/*
		 * Bridge接口定义桥梁，根据注入的对象，实现不同功能
		 * 依赖注入    ； 让Bridge Sourceable的实现类  松耦合
		 */
		Bridge bridge = new MyBridge();
		
		/*调用第一个对象*/
		Sourceable source1 = new SourceSub1();
		bridge.setSource(source1);
		bridge.method();
		
		/*调用第二个对象*/
		Sourceable source2 = new SourceSub2();
		bridge.setSource(source2);
		bridge.method();
	}
	public void fun(){
		//TODO
		java.util.logging.MemoryHandler a = null;
		java.util.logging.Formatter b=null;
	}
}
