package com.learn.hanjx.design.builder;

/**
 * 建造者模式（Builder）
 *（1）将构造逻辑提到单独的类中
 *（2）分离类的构造逻辑和表现
 */
public class Test {

	public static void main(String[] args) {
		Builder builder = new Builder();
		//构造逻辑
		builder.produceMailSender(10);
		builder.produceSmsSender(5);
		//打印一下
		builder.showList();
	}
}
