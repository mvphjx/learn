package com.learn.hanjx.design.prototype;

import java.io.IOException;


/**
Prototype（原型） 
作用：
（1）复制对象
（2）浅复制、深复制
JDK中体现：Object.clone；Cloneable
 */
public class Test {

	public static void main(String[] args) {
		SerializableObject serobj = new SerializableObject();
		serobj.setKey("hanjx");
		Prototype obj = new Prototype();
		obj.setObj(serobj);
		System.out.println("object:"+obj.getObj());		
		try {
			//浅克隆，只是属性复制，引用变量的地址相同
			Prototype clone = (Prototype) obj.clone();
			System.out.println("clone:"+clone.getObj());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		try {
			//深克隆，复制成员属性，并且创建新的对象
			//完全独立
			Prototype deepClone = (Prototype) obj.deepClone();
			System.out.println("deepClone:"+deepClone.getObj());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		
	}

}
