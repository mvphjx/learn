package com.learn.hanjx.assert1;
/**
 * 
开启assertion功能

if(假设成立)
{
     程序正常运行；
}
else
{
      报错&&终止程序！（避免由程序运行引起更大的错误）  
}
 */

public class TestPerson{
	private String name;
	public  TestPerson(String name){
   	 	boolean expression ="".equalsIgnoreCase(name);
   	 	assert expression;
        this.name = name;
    }
    public void setName(String nameStr){
        this.name = nameStr;
    }
    public String getName(){
         return this.name;
    }
}