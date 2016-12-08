package com.learn.hanjx.exception;

import java.util.TooManyListenersException;


public class Test {
		public void fun () throws Throwable {
			throw(new TooManyListenersException());
		}
		public void fun1 () throws AssertionError {
			throw(new AssertionError());
		}
		public static void main(String [] aaa){
			Test me = new  Test();
			try {
				me.fun();
			} catch (Throwable  e) {
				System.out.println("hi");
				e.printStackTrace();
			}
			System.out.println("end");
		}
	
	
}
