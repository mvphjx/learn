package com.learn.hanjx.design.decorator;

/**
Decorator（装饰器）

作用：为类添加新的功能；防止类继承带来的爆炸式增长
JDK中体现：
（1）java.io包
（2）java.util.Collections#synchronizedList(List)
 */
public class DecoratorTest {

	public static void main(String[] args) {
		Sourceable source = new Source();
		/*
		 * 将对象作为私有成员，可以防止功能爆炸式增长（收敛方法，只提供需要的方法）
		 * 安全性？  //TODO
		 */
		Sourceable obj = new Decorator(source);
		obj.method();
	}
	public  static void  test (){
		java.util.Collections.synchronizedList(null);
	}
}
