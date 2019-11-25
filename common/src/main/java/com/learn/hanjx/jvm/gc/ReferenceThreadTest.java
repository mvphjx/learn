package com.learn.hanjx.jvm.gc;
/**
 * jvm回收机制
 *
以下对象会被认为是root对象：
被启动类（bootstrap加载器）加载的类和创建的对象
jvm运行时方法区类静态变量(static)引用的对象
jvm运行时方法去常量池引用的对象
jvm当前运行线程中的虚拟机栈变量表引用的对象
本地方法栈中(jni)引用的对象

强引用（Strong Reference）	不符合垃圾收集
软引用（Soft Reference）	垃圾收集可能会执行，但会作为最后的选择
弱引用（Weak Reference）	符合垃圾收集
虚引用（Phantom Reference）	符合垃圾收集

 */
/*
测试线程常量会不会被回收
 */
import java.lang.ref.Reference;
import java.util.HashMap;
import java.util.Map;
public class ReferenceThreadTest  {
	private static int i;
	public static final Map<Integer, Reference> map = new HashMap<Integer, Reference>();
    public static void main(String[] args) {
    	ReferenceThreadTest  thread = new ReferenceThreadTest ();
    	while(true){
    		Thread t = new Thread(thread.new ReferenceObject()); 
    		t.start();
    		System.out.println("运行次数:"+i);
    		//查看java内存信息
    		System.gc();
    	    Runtime imp = Runtime.getRuntime();
    	    System.out.println("最大内存:"+imp.maxMemory()/1024);
    	    System.out.println("空闲内存:"+imp.freeMemory()/1024);
    	}
    	
    }
    class ReferenceObject extends Thread{
        public static final String   str="线程常量字符串！！！！！";
        public void run() {
            i++;  
        } 
    }
}
