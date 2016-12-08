package com.learn.hanjx.util.proxy;
/*
 * 获取动态代理对象
 */
import java.lang.reflect.Proxy;

public class TimeProxy {
	/**
	 * 参数为实现类
	 * 返回 实现接口的 对象
	 * @param obj
	 * @return
	 */
	public static Object getProxy(Object obj) {
		TimeHandler h = new TimeHandler(obj);
		/*
		 * @param   loader the class loader to define the proxy class
		 * @param   interfaces the list of interfaces for the proxy class
		 *          to implement （not null）
		 * @param   h the invocation handler to dispatch method invocations to
		 * @return  a proxy instance with the specified invocation handler of a
		 *          proxy class that is defined by the specified class loader
		 *          and that implements the specified interfaces
		 */
		Object newobj =Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), h);
		return newobj;
	}
}
