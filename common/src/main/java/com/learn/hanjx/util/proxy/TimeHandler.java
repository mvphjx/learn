package com.learn.hanjx.util.proxy;
/*
 * 动态代理处理器 
 * 目前是为了打印时间
 * 监控执行时间
 */
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TimeHandler implements InvocationHandler {
    /**
     * 此对象为  代理之前的对象
     * 如果操作代理后的对象（invoke中的Object proxy ），会产生递归调用，死循环
     */
    private Object delegate;

    /**
    *构造函数,设置代理对象.
    */
    public TimeHandler(Object delegate){
       this.delegate = delegate;
   }
    /**
    *构造函数
    */
    public TimeHandler(){
       this.delegate = null;
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
   public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      Object result = null;
       try {
    	   //为什么不相等呢
    	   assert proxy==delegate;
    	   Long time1 = System.currentTimeMillis();
           //调用代理对象delegate的method方法，并将args作为参数信息传入
    	   if(delegate==null){
    		   /*
    		    * 调用proxy的任何方法，都会引起无限递归
    		    * 不要这样做！Wrong！
    		    */
    		   assert proxy!=null;
    		   result = method.invoke(proxy, args);
    	   }else{    	
    		   //Right 正确写法
    		   result = method.invoke(delegate, args);
    	   }
           //等待当前线程 任务  执行完毕
           //同步  异步操作
           //TODO
           //输出信息
           StringBuffer sb = new StringBuffer("after process:");
           sb.append(method);
           sb.append("\n");
           sb.append("耗时:");
           sb.append(System.currentTimeMillis()-time1);
           sb.append("ms");
           System.out.println(sb);
       } catch (Exception e){    
    	   e.printStackTrace();
    	   System.err.println("发生异常:" + e.toString());
       }
       return result;
   }
}
