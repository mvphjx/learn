package com.learn.hanjx.distributed.simple;

public interface DLockTest {
	//通过线程池api实现 同步执行/关闭
	void test() throws Exception;
	//通过反射/代理模式  相关api 实现 同步执行/关闭
	void utiltest() throws Exception;

}
