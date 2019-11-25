package com.learn.Try.T2016.T10;
/*
 * StringBuffer是引用
 *  放到list中可能会变
 */
import java.util.ArrayList;
import java.util.List;

public class TestCollectionAndStringBuffer {
	public static void main(String args[]){
		StringBuffer sb = new StringBuffer();  
        List<Object> list = new ArrayList<>(); 
        //StringBuffer change
        sb.append("第一行数据");  
        list.add(0,sb);  
        sb.delete(0, sb.length());  
        sb.append("第二行数据");  
        list.add(1,sb); 
        //string change
        String s = "3"; 
        list.add(s);  
        s= "4";  
        list.add(s); 
        System.out.println(list); 
	}

}
