package com.learn.hanjx.aop.biz;
/**
 *业务逻辑类接口.    
 */
public interface ServiceObj {

    /**
     *执行业务.
     */
   /** 如果 与 其他接口多态，会报错
    *  public String process();
    */
	public String proc();
}