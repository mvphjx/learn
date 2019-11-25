package com.learn.hanjx.aop.jdkproxy.test;
/*
 * 深入地理解Java的反射和动态代理机制，
 * 同时对Spring中盛行的IOC和AOP的后台实现原理有了更加清晰的理解
 * AOP将通用基础功能和业务逻辑分开
 */
import java.lang.reflect.Proxy;

import org.junit.Test;

import com.learn.hanjx.aop.biz.BusinessObj;
import com.learn.hanjx.aop.biz.impl.BusinessObjImpl;
import com.learn.hanjx.aop.handler.LogInterceptor;
import com.learn.hanjx.aop.jdkproxy.Advisor;
import com.learn.hanjx.aop.jdkproxy.AopHandler;
import com.learn.hanjx.aop.jdkproxy.BeanFactory;
import com.learn.hanjx.aop.jdkproxy.BeforeMethodAdvisor;

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
        Class advisorCls =BeforeMethodAdvisor.class;
        //切面逻辑
        Advisor advisor = (Advisor) advisorCls.newInstance();
        aopHandler.setBeforeAdvisor(advisor);
        //动态代理obj
        BusinessObj obj =  aopHandler.getObject(BusinessObjImpl.class);
        try{
        	 obj.process();
        }catch(Exception e){
        	System.out.println(""); 
        	System.out.println("Exception:"+e.getMessage());       	
        }
        System.out.println("*************************以下为正确写法*********************************");       
        obj =  (BusinessObj) aopHandler.setObject(BusinessObjImpl.class.newInstance());
        try{
       	 	obj.process();
        }catch(Exception e){
       		System.out.println("Exception:"+e.getMessage());       	
        }
    }
   
   
    
}
