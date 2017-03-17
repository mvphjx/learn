package com.learn.hanjx.exceptionAndError;

import java.io.IOException;

public class MyException{
	//非编译必须throw
	public void fun () throws MyOtherException {
		throw(new MyOtherException());
	}
	public void fun (int  i){
		throw(new MyRuntimeException());
	}
	public static void main(String [] aaa){
		MyException me = new  MyException();
		try {
			me.fun();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			me.fun(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/*
 * RuntimeException 是那些可能在 Java 虚拟机正常运行期间抛出的异常的超类。 
可能在执行方法期间抛出但未被捕获的 RuntimeException 的任何子类都无需在 throws 子句中进行声明。 
*/
	class MyRuntimeException  extends RuntimeException{
		public MyRuntimeException(){
			super("运行时异常");
		}
	}
/*
* 非编译时异常
*/
	class MyOtherException  extends IOException{
		public MyOtherException(){
			super("非编译时异常");
		}
	}	
}
