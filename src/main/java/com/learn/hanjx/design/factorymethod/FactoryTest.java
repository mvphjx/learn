package com.learn.hanjx.design.factorymethod;

import com.learn.hanjx.design.iterator.Collection;

/**
工厂方法
凡是出现了大量的产品需要创建，并且具有共同的接口时，可以通过工厂方法模式进行创建。
 */
public class FactoryTest {

	public static void main(String[] args) {
		/*静态工厂方法
		 * （1）代替构造函数创建对象
		 * （2）方法名比构造函数清晰
		*/
		Sender sender = SendFactory.produceMail();
		sender = SendFactory.produce("mail");
		sender.Send();
		
		
		/*Factory Method（工厂方法） 
		作用：实现决定 实例化的类  （面向接口编程）
		JDK中体现：Collection.iterator方法
		
		
		ArrayList extends AbstractList
		List 接口的骨干实现 abstract class>
		AbstractList extends  AbstractCollection implements List
		此类提供 Collection 接口的骨干实现，以最大限度地减少了实现此接口所需的工作>
		AbstractCollection  implements Collection	

		HashMap extends AbstractMap
		AbstractMap implements Map
		
		*/
		/**Iterator（迭代器）**/
		/*
		 作用：将集合的迭代和集合本身分离
		JDK中体现：Iterator、Enumeration接口
		*/
		java.util.Collection<Object> list  = new java.util.ArrayList<Object>();
		java.util.Iterator<Object> it =list.iterator();//产生ArrayList<Object>的迭代器	
		it.next();
	}
}
