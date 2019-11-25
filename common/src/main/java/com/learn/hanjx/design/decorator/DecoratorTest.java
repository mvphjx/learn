package com.learn.hanjx.design.decorator;

/**
Decorator（装饰器）

作用：为类添加新的功能；防止类继承带来的爆炸式增长
JDK中体现：
（1）java.io包
（2）java.util.Collections#synchronizedList(List)

 Decorator（装饰器） VS  Proxy 代理模式
 The difference is the intent of the patterns. 目的不一致，都是事先方式都是包装（中间层）实现
 Proxy controls access to the object. 针对访问控制
 Decorator is used to add responsibilities to the object. 针对功能（接口、方法） 增删
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
