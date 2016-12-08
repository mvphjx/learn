package com.learn.hanjx.distributed.simple;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.learn.hanjx.distributed.common.thread.MultiThreadProcessor;
import com.learn.hanjx.distributed.common.thread.RunLock;
public class DLockTestImp  implements DLockTest{
	@Override
    public void test() throws Exception {
    	DistributedLockImpl distributedLock = new DistributedLockImpl();
    	Registry r =LocateRegistry.createRegistry(8888); 
 	    r.bind("lock1", distributedLock);
        MultiThreadProcessor processor = new MultiThreadProcessor("aa");
        for (int i = 0; i < 8; i++) {
            processor.addProcessor(new RunLock("aa" + i));
        }
        long s = System.currentTimeMillis();
        processor.start();
        //线程等待 关闭操作 完成
        processor.close();
        long e = System.currentTimeMillis();
        System.out.println(e - s+"ms");
        
    }
	@Override
    public void utiltest() throws Exception {
    	DistributedLockImpl distributedLock = new DistributedLockImpl();
    	Registry r =LocateRegistry.createRegistry(8888); 
 	    r.bind("lock1", distributedLock);
        MultiThreadProcessor processor = new MultiThreadProcessor("aa");
        for (int i = 0; i < 8; i++) {
            processor.addProcessor(new RunLock("aa" + i));
        }
        long s = System.currentTimeMillis();
        processor.start();
        long e = System.currentTimeMillis();
        System.out.println(e - s+"ms");
        
    }
    public static void main(String[] args) throws Exception {
    	DLockTest test = new DLockTestImp();
    	test.test();
    }
}

