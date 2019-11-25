package com.learn.hanjx.javase;

import org.junit.Test;

public class 常量池 {
	public static void main(String[] args) {
		
		
	}
	
	/**
	 * 常量池 与  final的值  地址相同
	 */
	@Test
	public  void test1() {
		String a = "a1";
		String b = "a";
		final String c = "a";
		
		/*
		 * 拼接后  地址不相同
		 * 所以拼接字符串会使用 StringBuffer
		 */
		System.out.println(a==(b+1));
		/*
		 * 地址相同
		 * final拼接  等同于   常量拼接
		 */
		System.out.println(a==(c+1));
		System.out.println(a==("a"+1));
		/*
		 * 同理 不相同
		 */
		System.out.println((b+1)==(c+1));
	}

}
