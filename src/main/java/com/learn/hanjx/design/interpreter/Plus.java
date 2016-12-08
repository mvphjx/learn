package com.learn.hanjx.design.interpreter;

public class Plus implements Expression {

	@Override
	public int interpret(Context context) {
		return context.getNum1()+context.getNum2();
	}
}
