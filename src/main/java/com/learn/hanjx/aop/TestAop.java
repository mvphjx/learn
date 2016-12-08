package com.learn.hanjx.aop;
/*
 * 深入地理解Java的反射和动态代理机制，
 * 同时对Spring中盛行的IOC和AOP的后台实现原理有了更加清晰的理解
 * AOP将通用基础功能和业务逻辑分开
 */
import java.io.File;
import java.lang.reflect.Proxy;

import org.junit.Test;

import com.learn.hanjx.aop.springaop.Advisor;
import com.learn.hanjx.aop.springaop.AopHandler;
import com.learn.hanjx.aop.springaop.BeanFactory;

public class TestAop {
	/**
     *测试方法.
     *@paramargs
     */
    public static void main(String[] args) {
       String  path = "config.xml";
       BeanFactory factory = new BeanFactory();
       factory.init(path);
       BusinessObj obj = (BusinessObj) factory.getBean("businessObj");
       obj.process();
       obj.show();
    }
    public void fun(){
    	/**
    	 * 一般写法
    	 * 不会触发动态代理机制，即不会运行  业务逻辑之外的
    	 * 日志记录、性能统计、安全控制、事务处理、异常处理功能（方法）
    	 */
    	BusinessObj proxy = new  BusinessObjImpl();
    	proxy.process();
    	
    }
    @Test
    public void test() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
    	/**
    	 *getObject
    	 *
    	 *wrong demo
    	 *递归调用 死循环
    	 */
    	//创建AOP处理器
        AopHandler aopHandler = new AopHandler();
        //根据aop字符串获取对应的类
        Class advisorCls = Class.forName("com.learn.hanjx.aop.springaop.BeforeMethodAdvisor");
        //创建该类的对象
        Advisor advisor = (Advisor) advisorCls.newInstance();
        aopHandler.setBeforeAdvisor(advisor);
        //动态代理obj
        Object obj =  aopHandler.getObject(Class.forName("com.learn.hanjx.aop.BusinessObjImpl"));
        try{
        	 BusinessObj busiobj2 = (BusinessObj) obj;
        	 busiobj2.process();
        }catch(Exception e){
        	System.out.println("");
        	System.out.println("----------------Exception--------------");
        	e.printStackTrace();
        	System.out.println("\n\n\n");       	
        }
    }
    @Test
    public void test2() throws ClassNotFoundException, InstantiationException, IllegalAccessException{    
    	AopHandler aopHandler = new AopHandler();
    	/**
    	 *setObject  类似factory bean调用
    	 */
        Object obj2 = Class.forName("com.learn.hanjx.aop.BusinessObjImpl").newInstance();
        obj2=aopHandler.setObject(obj2);
        try{
            BusinessObj busiobj2 = (BusinessObj) obj2;
            busiobj2.process();
        }catch(Exception e){
        	e.printStackTrace();
        }
        System.out.println("\n\n\n");
        /**
         * right demo
         */
        //创建一个日志拦截器
        Object obj3 = Class.forName("com.learn.hanjx.aop.BusinessObjImpl").newInstance();
        LogInterceptor interceptor = new LogInterceptor(obj3);
        //通过Proxy类的newProxyInstance()方法来获得动态的代理对象
        BusinessObj proxy = (BusinessObj) Proxy.newProxyInstance(
               BusinessObjImpl.class.getClassLoader(),
               BusinessObjImpl.class.getInterfaces(),
               interceptor);
        //执行动态代理对象的业务逻辑方法
        proxy.show();
        System.out.println("\n\n\n");
    }
    
}
