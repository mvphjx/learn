package com.learn.Try.T2016.T03;
/*
 * 对象引用作为形参，可不可以改变对象的值
 */
public class NewString {
	public String str;
	public NewString(String str){
		this.str=str;
	}
	public static  void main(String [] s11){
		String s = "1";
		change(s);
		System.out.println(s);
		String ss = new String ("1");
		change(ss);
		System.out.println(ss);
		NewString sss = new NewString("1");
		change(sss);
		System.out.println(sss.str);
	}
	static void change(String s){
		s="1"+s;
		System.out.println("change:"+s);
	}
	static void change(NewString s){
		s.str=s.str+"1";
		System.out.println("change:"+s.str);
	}

}
