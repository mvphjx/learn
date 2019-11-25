package com.learn.hanjx.javase.OO;
/**
 * 如果不是final类，会有被重写的可能（风险）。
 * 
 * final
 * 1 不能被继承，无法扩展
 * 2 安全，不会被重写（覆盖）
 * 3 高效。编译器在遇到调用final方法时候会转入内嵌机制，大大提高执行效率。
 * 
 * 父类无参构造器
 * 1 默认隐式调用无参构造器    如没有可供访问的无参构造器    就必须  显示调用有参构造器
 * 2 子类实例化 前  父类必须先实例化
 * 3 所有类都是 java.lang.Object的子类
 * 
 * @author han
 *
 */
public class OverrideDemo {
	public static void main(String args[]) {
		SuperClass s1 = new SubClass(20);
		s1.printA();
	}
}

class SuperClass {
	int a;
	SuperClass() {
		a = 10;
		System.out.println("SuperClass");
	}

	public void printA() {
		System.out.println("父类中a =" + a);
	}
}

class SubClass extends SuperClass {
	int a;
	void SuperClass(){
		System.out.println("SuperClass_child");
	}
	SubClass(int a) {
		this.a = a;
		System.out.println("SubClass");
	}

	public void printA() {
		System.out.println("父类中a= " + super.a);
		System.out.println("子类中a = " + a);
	}
}
