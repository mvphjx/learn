package com.learn.hanjx.distributed.common.thread;
/**
 * 操作  分布式锁的线程
 * 远程调用 实现锁控制
 */
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.concurrent.TimeoutException;

import com.learn.hanjx.distributed.common.demo.RMIServer;
import com.learn.hanjx.distributed.simple.DistributedLock;
import com.learn.hanjx.distributed.simple.DistributedLockImpl;

public class RunLock implements Runnable{
    public RunLock(String name) {
    	super();
    }


	@Override
	public void run() {
        try {
        	DistributedLock lock = (DistributedLock)Naming.lookup("rmi://localhost:"+RMIServer.port+"/lock1");  //注：通过接口拿 
            for (int i = 0; i < 1000; i++) {
                long token = lock.lock();
                lock.unlock(token);
            }
            System.out.println("end-" + Thread.currentThread().getId());
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
