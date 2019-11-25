package com.learn.hanjx.distributed.zookeeper.common;
/**
 * 实现此类，可以调用分布式锁的相关功能
 */
import com.learn.hanjx.distributed.zookeeper.DistributedLock;
 
public abstract class DistributedLockAble{
	DistributedLock lock;
	public DistributedLockAble(){
		
	}
    public DistributedLockAble(String config, String lockName) {
		lock = new DistributedLock(config, lockName);
	}
	// 锁方法实现
	public abstract Boolean isLock(String queueStr);
	// 解锁
	public abstract void unIpLock(String queueStr);
	
	
}