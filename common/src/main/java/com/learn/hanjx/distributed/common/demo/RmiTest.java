package com.learn.hanjx.distributed.common.demo;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.junit.Test;

public class RmiTest {
	/**
	 * 1 创建远程服务器，绑定对象 
	 * 2 客户端远程调用
	 */
	public static void main(String[] args) throws Exception {
		Remote ic = new IRMIImpl(); //具体实现类 
	    Registry r = LocateRegistry.createRegistry(8888); 
	    r.bind("mytask", ic); 
	    RMIClient.getRemoteObject();
	    RMIClient.getRemoteObject();
	}
	@Test
	public void clienttest() throws Exception{
		RMIClient.getRemoteObject();
	    RMIClient.getRemoteObject();
	}

}
