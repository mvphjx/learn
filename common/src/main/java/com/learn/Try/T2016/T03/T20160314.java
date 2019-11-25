package com.learn.Try.T2016.T03;
/*
 * 内部类作为接口
 * 匿名内部类
 */
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class T20160314 {
	interface Readable {
	    public int read(java.nio.CharBuffer cb) throws IOException;
	}
	class  Test implements Readable{

		@Override
		public int read(CharBuffer cb) throws IOException {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}
	class  TestSort implements Comparator{

		@Override
		public int compare(Object o1, Object o2) {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void  fun (){
		//匿名内部类
		//public static <T> void sort(List<T> list, Comparator<? super T> c)
		//sort第二个参数  需要一个实现了Comparator接口的一个类，
		//因为可能只用一次  这里使用匿名内部类
		Collections.sort(null,new  Comparator(){

			@Override
			public int compare(Object o1, Object o2) {
				// TODO Auto-generated method stub
				return 0;
			}
			
		});
		//使用有名字的内部类的写法
		Collections.sort(null,new TestSort());
		
		
	}
	abstract class Person {
		//这是一个抽象类
		public void  Han(){
			System.out.print("");		
		}
	    public abstract void eat();

	}
	class Person2{
		//这是一个普通类
	}
	public void main(String[] args) {
		//通过匿名内部类  获取一个  实现了接口或者抽象类的对象
		Person p = new Person() {
			@Override
			public void eat() {
				// TODO Auto-generated method stub
				
			}
        };
        Person2 p2= new Person2();
    }

}
