package com.learn.Try.T2016.T12;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.Test;

public class TestNull {  
    public static void main(String[] args) {  
    //HashMap  允许null-null键值对  
        Map<String,String> hashMap = new HashMap<String,String>();  
        hashMap.put("11", "ddd");  
        hashMap.put("1233", null);  
        hashMap.put(null, "wang");  
        hashMap.put(null, null);  
        System.out.println("HashMap以上代码运行成功");  
         
  //TreeMap  允许value值为null，不允许key值为null  
       TreeMap<String,String> treeMap = new TreeMap<String,String>();  
         
       //Map放入第一个元素时不会调用比较器，所以不会调用比较器，不会出现NullPointerException  
       //以下一行代码执行时不会报错，但当treeMapp中放入元素大于1时，就会调用比较器，出现NullPointerException  
       // treeMap.put(null, null);  
       treeMap.put("ddd", null);  
       treeMap.put("sss", null);  
       System.out.println("TreeMap以上代码运行成功");  
         
         
  //HashSet  
       Set<String> hashSet = new HashSet<String>();  
       hashSet.add(null);  
       hashSet.add("ddd");  
       System.out.println("HashSet以上代码运行成功");  
    
  //TreeSet  
       Set<String> treeSet = new TreeSet<String>();  
       //以下两行代码执行时，会报错。理由同TreeMap  
       //treeSet.add(null);  
       treeSet.add("sss");  
       System.out.println("TreeSet以上代码运行成功");  
         
  //ArrayList  
       List<String> arrayList = new ArrayList<String>();  
       arrayList.add(null);  
       arrayList.add("dd");  
       System.out.println("ArrayList以上代码运行成功");  
         
  //LinkedList  
       List<String> linkedList = new LinkedList<String>();  
       linkedList.add(null);  
       linkedList.add("ddd");  
       System.out.println("LinkedList以上代码运行成功");  
  }  
    @Test
    public void test(){
    	TestNull.addNull(new HashSet<String>());      
    	TestNull.addNull(new TreeSet<String>());  
    	TestNull.addNull(new LinkedHashSet<String>());  
    	TestNull.addNull(new ArrayList<String>());  
    	TestNull.addNull(new LinkedList<String>());  
    }
    
    @SuppressWarnings({ "finally", "unchecked", "rawtypes" })
	public static boolean addNull(Collection t){
    	boolean bool = false;
    	try {
    		t.add(null);
    		bool = true;
    		//t.forEach(System.out::println);
		} catch (Exception e) {
			bool=false;
			//e.printStackTrace();
		}finally{
			System.out.println(t.getClass().getSimpleName()+" addNull():"+bool);
			return bool;
		}
    }
}  