package com.learn.hanjx.design.proxy;

public class Proxy implements Sourceable {

	private Source source;
	public Proxy(Source source){
		super();
		this.source = source;
	}
	@Override
	public void method() {
		before();
		source.method();
		atfer();
	}
	private void atfer() {
		System.out.println("after proxy!");
	}
	private void before() {
		System.out.println("before proxy!");
	}
}
