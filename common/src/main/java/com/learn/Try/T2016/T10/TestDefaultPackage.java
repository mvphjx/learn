package com.learn.Try.T2016.T10;
/*
 * 验证  包级私有 作用权限（作用域）
 * 
 * 就是同一个package下，不包括其他 上级 下级 同胞文件夹  的类
 * 
 * package com.learn.Try.T2016.T10;
 * 相等
 * com.learn.Try.T2016.T10.DefaultPackage
 */

public class TestDefaultPackage {
	public static void main(String[] args) {
		com.learn.Try.DefaultPackage d1 = new com.learn.Try.DefaultPackage();
		d1.getId();
		
		com.learn.Try.T2016.DefaultPackage d2 = new com.learn.Try.T2016.DefaultPackage();
		d2.getId();

		//可以调用  包内私有  方法
		com.learn.Try.T2016.T10.DefaultPackage d3 = new com.learn.Try.T2016.T10.DefaultPackage();
		d3.getId();
		d3.getName();
		
		
		com.learn.Try.T2016.T10.inter.DefaultPackage d4 = new com.learn.Try.T2016.T10.inter.DefaultPackage();
		d4.getId();
		
		
		com.learn.Try.T2016.T08.DefaultPackage d5 = new com.learn.Try.T2016.T08.DefaultPackage();
		d5.getId();
		
		
	}

}
