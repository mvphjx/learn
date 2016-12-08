package com.learn.hanjx.distributed.simple;
/* 
* 这个接口继承自Remote，每一个定义的方法都必须抛出一个RemoteException异常对象 
* 我们可供远程调用的方法就是通过这里开公开 
*/ 
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public interface DistributedLock extends Remote {

    long lock() throws RemoteException, TimeoutException;

    long tryLock(long time, TimeUnit unit) throws RemoteException, TimeoutException;

    void unlock(long token) throws RemoteException;

}