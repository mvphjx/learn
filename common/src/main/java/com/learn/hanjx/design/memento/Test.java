package com.learn.hanjx.design.memento;

/**
Memento（备忘录）
作用：保持对象状态，需要时可恢复
JDK中体现：未发现
 */
public class Test {

	public static void main(String[] args) {
		
		// 创建原始类
		Original origi = new Original("egg");

		// 创建备忘录
		Storage storage = new Storage(origi.createMemento());

		// 修改原始类的状态
		System.out.println("初始化状态为：" + origi.getValue());
		origi.setValue("niu");
		System.out.println("修改后的状态为：" + origi.getValue());

		// 回复原始类的状态
		origi.restoreMemento(storage.getMemento());
		System.out.println("恢复后的状态为：" + origi.getValue());
	}
}
