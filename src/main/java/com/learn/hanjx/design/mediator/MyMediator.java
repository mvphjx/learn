package com.learn.hanjx.design.mediator;

public class MyMediator implements Mediator {

	private User user1;
	private User user2;
	
	public User getUser1() {
		return user1;
	}

	public User getUser2() {
		return user2;
	}
/**
 * 缺点
 * 包含了大量同事之间的交互细节，可能会导致具体中介者类
 * 非常复杂，使得系统难以维护。
 */
	@Override
	public void createMediator() {
		user1 = new User1(this);
		user2 = new User2(this);
	}

	@Override
	public void workAll() {
		user1.work();
		user2.work();
	}
}
