package com.learn.hanjx.exception;
/**
 * 包装类 Integer int  自动拆包（自解箱），可能出现空指针
 * @author han
 *
 */
public class IntegerTest {
public static void main(String[] args) {
	int n =getNum();
}
public static int getNum(){
	Integer  i = null;
	return i;
}
}
