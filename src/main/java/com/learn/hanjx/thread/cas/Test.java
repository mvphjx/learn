package com.learn.hanjx.thread.cas;

import java.util.concurrent.atomic.AtomicBoolean;

/*非阻塞算法
 * http://ifeve.com/non-blocking-algorithms/
 * 
 * 
 * http://ifeve.com/compare-and-swap/
 * http://www.cnblogs.com/lintong/p/4373723.html
 * Compare And Swap
 * 在Java并发包中有这样一个包，java.util.concurrent.atomic，
 * 该包是对Java部分数据类型的原子封装，在原有数据类型的基础上，提供了原子性的操作方法，保证了线程安全。
 */
public class Test {
	
	public static class MyLock {
		    private AtomicBoolean locked = new AtomicBoolean(false);
		    public boolean lock() {
		        return locked.compareAndSet(false, true);
		    }
		}


}
