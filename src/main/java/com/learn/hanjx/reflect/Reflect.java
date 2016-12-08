package com.learn.hanjx.reflect;
/*
 * 反射
 * 类似动态语言的特性
 * 通过反射机制访问java对象的属性，方法，构造方法等
 */
public class Reflect {
	private String id;
	public static void main(String[] args) throws Exception{  
            //以前的方式：  
		    /* 
		    User u = new User(); 
		    u.age = 12; //set 
		    System.out.println(u.age); //get 
		    */           
		    //获取类  
		    Class c = Class.forName("com.learn.hanjx.reflect.Reflect");  
		    //获取id属性  
		    java.lang.reflect.Field idF = c.getDeclaredField("id");  
		    //实例化这个类赋给o  
		    Object o = c.newInstance();  
		    //打破封装  
		    idF.setAccessible(true); //使用反射机制可以打破封装性，导致了java对象的属性不安全。  
		    //给o对象的id属性赋值"110"  
		    idF.set(o, "110"); //set  
		    //get  
		    System.out.println(idF.get(o));  
		}
}
