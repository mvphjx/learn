package com.learn.hanjx.design.singleton;

public class Singleton {

	/* 私有构造方法，防止被实例化 */
	private Singleton() {
		System.out.println("Singleton构造方法");
	}

	/* 此处使用一个内部类来维护单例 */
	private static class SingletonFactory {
		/* 单例模式使用内部类来维护单例的实现，
		 * JVM内部的机制能够保证当一个类被加载的时候，
		 * 这个类的加载过程是线程互斥的。
		 * 这样当我们第一次调用getInstance的时候，
		 * JVM能够帮我们保证instance只被创建一次，
		 * 并且会保证把赋值给instance的内存初始化完毕
		 */
		private static Singleton instance = new Singleton();
	}

	/* 获取实例 */
	public static Singleton getInstance() {
		return SingletonFactory.instance;
	}

	public static void  main(String [] args){
		Singleton.getInstance();
	}
}