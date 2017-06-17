package com.learn.hanjx.collection;
/*
 * Map遍历方式
 * keySet
 * values
 * entrySet  （Set<Entry> iterator）
 * 迭代器API  remove()可用
 * 
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class MapIterator {
	public static void main(String[] args) {


		  Map<String, String> map = new HashMap<String, String>();
		  map.put("1", "value1");
		  map.put("2", "value2");
		  map.put("3", "value3");
		  
		  //第一种：普遍使用，二次取值
		  System.out.println("通过Map.keySet遍历key和value：");
		  for (String key : map.keySet()) {
		   System.out.println("key= "+ key + " and value= " + map.get(key));
		  }
		  
		  //第二种
		  System.out.println("通过Map.entrySet使用iterator遍历key和value：");
		  Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
		  while (it.hasNext()) {
		   Map.Entry<String, String> entry = it.next();
		   it.remove();
		   System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
		  }
		  
		  //第三种：推荐，尤其是容量大时
		  System.out.println("通过Map.entrySet遍历key和value");
		  for (Map.Entry<String, String> entry : map.entrySet()) {
		   System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
		  }

		  //第四种
		  System.out.println("通过Map.values()遍历所有的value，但不能遍历key");
		  for (String v : map.values()) {
		   System.out.println("value= " + v);
		  }
		 }
	
	@Test
	public void test(){
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<String> list1 = new ArrayList<>();
		list1.add("123");
		list1.add("234");
		list1.add("345");
		List<String> list2 = new ArrayList<>();
		list2.add("12");
		list2.add("34");
		map.put("1", list1);
		map.put("2", list2);
		for (Map.Entry<String, List<String>> entry : map.entrySet()) {
			   System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
	    }
	}
  
 
}
