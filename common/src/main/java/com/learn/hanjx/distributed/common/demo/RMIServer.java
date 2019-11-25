package com.learn.hanjx.distributed.common.demo;

import java.rmi.Naming; 
import java.rmi.registry.LocateRegistry; 
import java.rmi.registry.Registry; 

/* 
* 远程对象的注册类 该类应该在服务器端执行，执行之后 
* 该机器将变为RMI服务器 客户端可以通过正确的url来访问 
* 服务器上的远程对象，执行对外报露的方法 
*/ 

public class RMIServer { 
    public static int port = 8888; 

    /* 
     * 创建一个Registry对象. 
     * LocateRegistry用于获取名字服务或创建名字服务. 
     * 调用LocateRegistry.createRegistry(int port)方法可以在某一特定端口创建名字服务,从而用户无需再手工启动rmiregistry 
     * @return 返回一个Registry对象 
     */ 
    private static Registry createRegistry() { 
        Registry registry = null; 
        try { 
            registry = LocateRegistry.getRegistry(port); //如果该端口未被注册，则抛异常 
            registry.list(); //拿到该端口注册的rmi对象 
        } catch (final Exception e) { 
            try { 
                registry = LocateRegistry.createRegistry(port);//捕获异常，端口注册 
            } catch (final Exception ee) { 
                ee.printStackTrace(); 
            } 
        } 
        return registry; 
    } 

    /** 
     * 将对象注册到rmi服务器上 
     */ 
    public static void bind() { 
        Registry registry =  createRegistry(); 
        try { 
            IRMIImpl impl = new IRMIImpl(); 
            registry.rebind("mytask", impl); //这就是绑定,client里lookup必须和"mytast"一样才能远程调用impl 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
    } 

   public static void main(String[] args) { 
        try { 
            bind(); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
    } 
} 