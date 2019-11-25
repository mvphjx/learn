package com.learn.hanjx.aop.cglibproxy.test;

import com.learn.hanjx.aop.biz.BusinessObj;
import com.learn.hanjx.aop.biz.impl.BizObjectImpl;
import com.learn.hanjx.aop.biz.impl.BusinessObjImpl;
import com.learn.hanjx.aop.cglibproxy.Base;
import com.learn.hanjx.aop.cglibproxy.CglibProxy;
import com.learn.hanjx.aop.cglibproxy.Factory;
/**
 * 
 * @author han
 *
 */
public class Test {  
	public Test(){
		
	}
	@org.junit.Test
    public  void baseTest() {  
        CglibProxy proxy = new CglibProxy();  
        // base为生成的增强过的目标类  
        Base base = Factory.getInstance(proxy,Base.class);  
        base.add();  
    }  
	@org.junit.Test
    public  void businessObjTest() {  
        CglibProxy proxy = new CglibProxy();  
        // base为生成的增强过的目标类  
        BusinessObj base = Factory.getInstance(proxy,BusinessObjImpl.class);  
        base.process();;  
    }  
	@org.junit.Test
    public  void bizObjectImplTest() {  
		/**
		 * 可以摆脱接口的限制
		 */
        CglibProxy proxy = new CglibProxy();  
        // base为生成的增强过的目标类  
        BizObjectImpl base = Factory.getInstance(proxy,BizObjectImpl.class);  
        base.process();
        base.proc();
    }  
}  