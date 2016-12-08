package com.learn.hanjx.design.singleton;

import org.junit.Test;

/**Singleton（单例） **/
public class Ttest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/**Singleton（单例） **/
		/*
		 * 作用：保证类只有一个实例；提供一个全局访问点
			JDK中体现：（1）Runtime
		 */ 
		java.lang.Runtime.getRuntime();
	}
	/*
	 * 
	 */
	@Test
	public   void test() throws InterruptedException{
		Thread.sleep(1000L);
		//懒加载
		Singleton.getInstance();
		
		
		
		}

}
