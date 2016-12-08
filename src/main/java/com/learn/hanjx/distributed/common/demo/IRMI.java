package com.learn.hanjx.distributed.common.demo;

import java.rmi.Remote; 
import java.rmi.RemoteException; 

/* 
* 这个接口继承自Remote，每一个定义的方法都必须抛出一个RemoteException异常对象 
* 我们可供远程调用的方法就是通过这里开公开 
*/ 
public interface IRMI extends Remote{ 
    public String invoke() throws RemoteException; 
} 