package com.learn.hanjx.aop.test;
/**
 * 动态代理类不仅简化了编程工作，而且提高了软件系统的扩展性和可维护性。
 * 我们可以通过实现java.lang.reflect.InvocationHandler接口提供一个执行处理器，
 * 然后通过java.lang.reflect.Proxy得到一个代理对象，
 * 通过这个代理对象来执行业务逻辑方法,在业务逻辑方法被调用的同时，自动调用会执行处理器。
 */
import java.lang.reflect.Proxy;

import org.junit.Test;

import com.learn.hanjx.aop.biz.BusinessObj;
import com.learn.hanjx.aop.biz.ServiceObj;
import com.learn.hanjx.aop.biz.impl.BizObjectImpl;
import com.learn.hanjx.aop.biz.impl.BusinessObjImpl;
import com.learn.hanjx.aop.handler.LogInterceptor;

public class TestLog {
	/**
     *测试方法.
     *@paramargs
     *@throwsException
     */
    public static void main(String[] args) throws Exception {
       BusinessObj obj = new BusinessObjImpl();
       //创建一个日志拦截器
       LogInterceptor interceptor = new LogInterceptor(obj);
       //通过Proxy类的newProxyInstance()方法来获得动态的代理对象
       BusinessObj proxy = (BusinessObj) Proxy.newProxyInstance(
              BusinessObjImpl.class.getClassLoader(),
              BusinessObjImpl.class.getInterfaces(),
              interceptor);
       //执行动态代理对象的业务逻辑方法
       proxy.process();
       proxy.show();
    }
    @Test
    public void testMore() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        /**
         * jdk Proxy 必须返回接口的实现类，多个接口只能返回一个
         */
    	BizObjectImpl obj = new BizObjectImpl();
        //创建一个日志拦截器
        LogInterceptor interceptor = new LogInterceptor(obj);
        //通过Proxy类的newProxyInstance()方法来获得动态的代理对象
        BusinessObj proxy = (BusinessObj) Proxy.newProxyInstance(
        		BizObjectImpl.class.getClassLoader(),
        		BizObjectImpl.class.getInterfaces(),
               interceptor);
        //执行动态代理对象的业务逻辑方法
        proxy.process();
        proxy.show();
        
        
    }
}
