package com.learn.hanjx.distributed.common.demo;

import java.rmi.Naming; 

public class RMIClient { 
    /** 
     * 调用远程对象中的方法 
     * @throws Exception 
     */ 
    public static void getRemoteObject() throws Exception{ 

    /*得到远程发布的服务 
    返回与指定 name 关联的远程对象的引用（一个stub）*/ 
        IRMI obj = (IRMI)Naming.lookup("rmi://localhost:"+RMIServer.port+"/mytask");  //注：通过接口拿 

        System.out.println(obj.invoke()); //调用远程服务的方法 
    } 

    public static void main(String[] args) { 
        try { 
            getRemoteObject(); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
    } 
} 