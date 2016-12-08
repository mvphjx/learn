package com.learn.hanjx.distributed.common.demo;

import java.rmi.RemoteException; 
import java.rmi.server.UnicastRemoteObject; 

/* 
* 远程对象的实现 
* 公开方法的具体实现就是这里定义的 
*/ 
public class IRMIImpl extends UnicastRemoteObject implements IRMI { 

    protected IRMIImpl() throws RemoteException { 
        super(); // 这个实现必须有一个显式的构造函数，并且要抛出一个RemoteException异常 
    } 

    private static final long serialVersionUID = 6131922116577454476L; 

    public String invoke() throws RemoteException {  //该方法公开 
        return "hello,world!"; 
    } 

    public String tryInvoke() throws RemoteException{ //该方法未公开，若要公开请在接口中定义 
       return "try to remote me"; 
    } 
} 