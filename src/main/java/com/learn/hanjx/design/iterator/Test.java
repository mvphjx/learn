package com.learn.hanjx.design.iterator;

/**
 * Iterator（迭代器）

作用：将集合的迭代和集合本身分离
JDK中体现：Iterator、Enumeration接口

迭代器优点之一：能够快速遍历集合。
迭代器的优点之二：能够实现不重新代码就可以应用于不能的容器类型。
好处是可以遍历时  进行删除操作？
 */
public class Test {

	public static void main(String[] args) {
		Collection collection = new MyCollection();
		Iterator it = collection.iterator();
		
		while(it.hasNext()){
			System.out.print(it.next()+" ");
		}
	}
}
