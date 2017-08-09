package com.learn.hanjx.design.singleton;
/**
 * 把创建和获取方法分开
 * 以便减小同步粒度，并且满足线程安全
 * @author hanjianxiang
 *
 */
public class SingletonT {
	private static SingletonT s;
	/* 私有构造方法，防止被实例化 */
	private SingletonT() {
	}
	
	/* 创建方法   进行同步保证线程安全*/
	private static synchronized SingletonT  SingletonFactory (){
		if(s==null){
			s=new SingletonT();
		}
		return s;
	}

	/* 获取实例   不需要同步 */
	public static SingletonT getInstance() {
		if(s==null){
			SingletonFactory();
		}
		return s;
	}
}