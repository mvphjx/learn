package com.learn.hanjx.design.interpreter;

/**
 * Interpreter(解释器)模式
 * 例如正则表达式
 */
public class Test {

	public static void main(String[] args) {

		// 计算9+2-8的值
		int result = new Minus().interpret((new Context(new Plus()
				.interpret(new Context(9, 2)), 8)));
		System.out.println(result);
	}
}
