package com.learn.hanjx.assert1;

import org.junit.Test;

public class DoTest {
	public DoTest(){
		//开启断言功能
		this.getClass().getClassLoader().setDefaultAssertionStatus(true);
	}
    @Test
    public void test(){
    	TestPerson personObj = new TestPerson("Test");  
    	personObj.getName();
    	System.out.println("???");
    }
    @Test
    public void test2(){
    	try{
        	TestPerson personObj = new TestPerson("");  
        	personObj.getName();	
    	}catch(AssertionError e){
    		e.printStackTrace();
    	}finally{
    		System.out.println("AssertionError/断言不通过");
    	}
    }
}
