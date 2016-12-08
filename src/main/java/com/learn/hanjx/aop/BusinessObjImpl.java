package com.learn.hanjx.aop;
/**
 * 业务逻辑对象实现类.
 */
public class BusinessObjImpl implements BusinessObj {
       /**
        * 执行业务.
        */
       public void process() {
              System.out.println("BusinessObjImpl执行业务逻辑process");
       }

	@Override
	public void show() {
		System.out.println("BusinessObjImpl执行业务逻辑show");
		
	}
}