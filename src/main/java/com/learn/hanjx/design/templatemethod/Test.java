package com.learn.hanjx.design.templatemethod;

/**
Template method（模板方法）
作用：定义算法的结构，子类只实现不同的部分
JDK中体现：ThreadPoolExecutor.Worker
 */
public class Test {

	public static void main(String[] args) {
		String exp = "8+8";
		AbstractCalculator cal = new Plus();
		int result = cal.calculate(exp, "\\+");
		System.out.println(result);
	}
}
