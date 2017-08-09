package com.learn.hanjx.aop.jdkproxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.InvocationHandler;

import org.hibernate.annotations.Persister;
/**
 * AOP处理器.   
可生成代理对象，同时可以根据设置的前置或后置处理对象分别在方法执行前后执行一些另外的操作
 */
public class AopHandler implements InvocationHandler {   
       //需要代理的目标对象 
       private Object target;   
       //方法前置顾问 
       Advisor beforeAdvisor;
       //方法后置顾问
       Advisor afterAdvisor;

       /**
        * 设置代理目标对象，并生成动态代理对象. 
        * @param target 代理目标对象
        * @return 返回动态代理对象
        */
       public Object setObject(Object target) { 
              //设置代理目标对象
              this.target = target;   
              //根据代理目标对象生成动态代理对象
              Object obj = Proxy.newProxyInstance(
                            target.getClass().getClassLoader(),   
                            target.getClass().getInterfaces(),this); 
              return obj;   
       }  
       
       /**
        * 生成动态代理对象. 
        * 
        * 这是一个错误的方法
        * 	必须生成 缓存 目标对象
        */
       @Deprecated
       public <T>T getObject(Class<T> c) { 
              //设置代理目标对象
              //根据代理目标对象生成动态代理对象
    	   this.target = Proxy.newProxyInstance(
                   c.getClassLoader(),   
                   c.getInterfaces(),this);
              return (T)this.target;
       }  

       /**
        * 若定义了前置处理，则在方法执行前执行前置处理
        * 若定义了后置处理，则在方法调用后调用后置处理.
        * @param proxy 代理对象
        * @param method 调用的业务方法
        * @param args 方法的参数
        * @return 返回结果信息
        * @throws Throwable
        */ 
       @Override
       public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
              //进行业务方法的前置处理
              if (beforeAdvisor != null) {
                     beforeAdvisor.doInAdvisor(proxy, method, args);
              }
              //执行业务方法
              Object result = method.invoke(target, args);   
              //进行业务方法的后置处理
              if (afterAdvisor != null) { 
                     afterAdvisor.doInAdvisor(proxy, method, args);
              }
              //返回结果对象
              return result;   
       }

       /**
        * 设置方法的前置顾问.
        * @param advisor 方法的前置顾问
        */
       public void setBeforeAdvisor(Advisor advisor) {   
              this.beforeAdvisor = advisor;   
       }   

       /**
        * 设置方法的后置顾问.
        * @param advisor 方法的后置顾问
        */
       public void setAfterAdvisor(Advisor advisor) {   
              this.afterAdvisor = advisor;   
       }   
}