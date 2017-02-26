package com.learn.hanjx.aop.biz.impl;

import com.learn.hanjx.aop.biz.BusinessObj;
import com.learn.hanjx.aop.biz.ServiceObj;

public class BizObjectImpl implements BusinessObj,ServiceObj {



	@Override
	public void show() {
		System.out.println("BizObject执行业务逻辑show");
		
	}

	@Override
	public String proc() {
		System.out.println("BizObject执行业务逻辑proc");
		return null;
	}

	@Override
	public void process() {
		System.out.println("BizObject执行业务逻辑process");
		
	}


}
