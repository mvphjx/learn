package com.learn.hanjx.aop.handler;
/**
 运行LogInterceptor类我们可以发现，它实现了前面所需要的功能，但是很好的将业务逻辑方法的代码和日志记录的代码分离开来，
 并且所有的业务处理对象都可以利用该类来完成日志的记录，防止了重复代码的出现，
 增加了程序的可扩展性和可维护性，从而提高了代码的质量。
 */
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *日志拦截器，用来进行日志处理.     
 */
public class LogInterceptor implements InvocationHandler {
     private Object delegate;

     /**
     *构造函数,设置代理对象.
     */
     public LogInterceptor(Object delegate){
        this.delegate = delegate;
    }

    /**
     *方法的调用.
     *@param proxy 执行getclass之外的方法 会形成递归死循环
     *@param method对应的方法
     *@param args方法的参信息
     *@return 返回操作结果对象
     *@throws Throwable
     */
     @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
       Object result = null;
        try {            
            System.out.println("before process:" + method);
            //调用代理对象delegate的method方法，并将args作为参数信息传入
            result = method.invoke(delegate, args);
            System.out.println("after process:" + method);
        } catch (Exception e){         
        System.err.println("发生异常:" + e.toString());
        }
        return result;
    }

    
}
