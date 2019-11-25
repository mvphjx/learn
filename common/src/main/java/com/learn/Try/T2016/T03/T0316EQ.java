package com.learn.Try.T2016.T03;
/*
 * ==  是比较的  内存中的地址
 * equals  是调用的   这个类的  equals  方法。。。
 * （如果没有重新equals  方法   两者结果一样）
 */
public class T0316EQ {
	public static void main(String [] args){
		String str1 = "a";
		String str2 = new String("a");
		System.out.println("==运行结果:"+(str1==str2));
		System.out.println("equals运行结果:"+str1.equals(str2));
		System.out.println("fun(8)运行结果:"+fun(8));
	}
	private static int fun(int num){
		if(num==1){
			return 10;
		}
		return fun(num-1)+2;
	}

}
