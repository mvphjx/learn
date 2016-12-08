package com.learn.hanjx.design.adpter;

/**适配器
 * 作用：使不兼容的接口相容
 */
public class AdapterTest {

	public static void main(String[] args) {
		Source source = new Source();
		Targetable target = new Wrapper(source);
		target.method1();
		target.method2();
	}
	public void fun (){
		/*
		 * java.io.InputStreamReader
		 * 兼容抽象类
		 * java.io.Reader
		 * java.io.InputStream
		 */
		java.io.Reader r=  new java.io.InputStreamReader(System.in);
	}
}
