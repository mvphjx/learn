package com.learn.hanjx.design.strategy;

public class Minus extends AbstractCalculator implements ICalculator {

	@Override
	public int calculate(String exp) {
		int arrayInt[] = split(exp,"-");
		return arrayInt[0]-arrayInt[1];
	}

	@Override
	public int calculate(int num1, int num2) {
		return num1-num2;
	}

}
