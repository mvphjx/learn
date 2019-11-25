package com.learn.hanjx.design.visit;

public interface Subject {
	public void accept(Visitor visitor);
	public String getSubject();
}
