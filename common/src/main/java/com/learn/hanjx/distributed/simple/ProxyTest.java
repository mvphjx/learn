package com.learn.hanjx.distributed.simple;

import org.junit.Test;

import com.learn.hanjx.util.proxy.TimeProxy;

public class ProxyTest{
	@Test
    public void test() throws Exception{
		DLockTest test = new DLockTestImp();
    	DLockTest test2 = (DLockTest) TimeProxy.getProxy(test);
    	test2.utiltest();
	}
}
