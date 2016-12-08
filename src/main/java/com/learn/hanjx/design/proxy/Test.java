package com.learn.hanjx.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
Proxy（代理）
作用：
（1）透明调用被代理对象，无须知道复杂实现细节
（2）增加被代理类的功能
JDK中体现：动态代理；RMI
 */
public class Test {

	public static void main(String[] args) {
		Source s = new Source();
		Sourceable source = new Proxy(s);
		source.method();
	}
	/*
	 * JDK中体现：动态代理
	 * 需要实现InvocationHandler.invoke
	 * 
	 * Proxy.newProxyInstance获取代理类的对象
	 */
	public void fun(){
		Object obj = new Object();
		LogInterceptor in = new LogInterceptor(obj);
		/*
		 * 参数：
			loader - 定义代理类的类加载器
			interfaces - 代理类要实现的接口列表
			h - 指派方法调用的调用处理程序 
		        返回：
			一个带有代理类的指定调用处理程序的代理实例，它由指定的类加载器定义，并实现指定的接口 
		 */
		Object proxy =  java.lang.reflect.Proxy.newProxyInstance(
				Object.class.getClassLoader(),
				Object.class.getInterfaces(),
	              in);
		proxy.hashCode();
	}
    //  代理类  处理程序 实现类
	class LogInterceptor implements InvocationHandler {
	     private Object delegate;
	     /**
	     *构造函数,设置代理对象.
	     */
	     public LogInterceptor(Object delegate){
	        this.delegate = delegate;
	    }

	    /**
	     *方法的调用.
	     *@paramproxy
	     *@parammethod对应的方法
	     *@paramargs方法的参信息
	     *@return返回操作结果对象
	     *@throwsThrowable
	     */
		@Override
		//理解为 将obj.method(args)进行了封装
		public Object invoke(Object obj, Method method, Object[] args)
				throws Throwable {
		       Object result = null;
		        try {            
		            System.out.println("before process:" + method);
		            result = method.invoke(delegate, args);
		            System.out.println("after process:" + method);
		        } catch (Exception e){         
		        System.err.println("发生异常:" + e.toString());
		        }
		        return result;
		}
		
	}
	

}
