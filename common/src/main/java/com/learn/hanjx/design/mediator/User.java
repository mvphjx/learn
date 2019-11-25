package com.learn.hanjx.design.mediator;

public abstract class User {
	
	private Mediator mediator;
	
	public Mediator getMediator(){
		return mediator;
	}
	/**
	 * 构建方法
	 * 适用场景
在以下情况下可以考虑使用中介者模式：
(1) 系统中对象之间存在复杂的引用关系，系统结构混乱且难以理解。
(2) 一个对象由于引用了其他很多对象并且直接和这些对象通信，导致难以复用该对象。
(3) 想通过一个中间类来封装多个类中的行为，而又不想生成太多的子类。
可以通过引入中介者类来实现，在中介者中定义对象交互的公共行为，如果需要改变行为则可以增加新的具体中介者类。
	 */
	public User(Mediator mediator) {
		this.mediator = mediator;
	}

	public abstract void work();
}
