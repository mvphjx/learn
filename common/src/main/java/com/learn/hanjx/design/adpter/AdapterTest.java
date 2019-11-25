package com.learn.hanjx.design.adpter;

import java.io.InputStreamReader;
import java.io.Reader;

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

    /**兼容抽象类
     * {@link  java.io.Reader}
     * {@link java.io.InputStreamReader}
     * {@link  java.io.InputStream}
     *
     * Reader = InputStreamReader(InputStream)
     */
	public void fun (){
		Reader r=  new InputStreamReader(System.in);
	}
}
