package com.learn.Try.T2016.T08;
/*
 * 栈是存放线程调用方法时存储局部变量表，操作，方法出口等与方法执行相关的信息，栈大小由Xss来调节，方法调用层次太多会撑爆这个区域
 */
public class Digui {
	public static void main(String[] args) {
		getNum();
	}
	public static int getNum(){
		return getNum();
	}
}
