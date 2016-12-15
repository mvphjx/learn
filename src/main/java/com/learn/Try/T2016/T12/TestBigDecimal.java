package com.learn.Try.T2016.T12;

import java.math.BigDecimal;

/**
 * 测试一下 
 * @author han
 *
 */
public class TestBigDecimal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BigDecimal big = new BigDecimal("1.9511111111111111");
		BigDecimal big2 = new BigDecimal("2.1");
		System.out.println(big.toString());
		System.out.println(big2.multiply(big).toString());
		//除法最好指定精度
		//Non-terminating decimal expansion; no exact representable decimal result.
		System.out.println(big2.divide(big).toString());
	}

}
