package com.learn.hanjx.collection.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.junit.Test;

public class ListFilter {
	/*
	 * jdk8 实现list对元素的过滤*/
	public static void main(String[] args) {
		List<String> list1 = new ArrayList();  
        List<String> list2 = new ArrayList();  
        list1.add("1");  
        list1.add("1");  
        list1.add("2");  
        list1.add("3");  
  
        list2 = list1.stream().filter(s -> s != "2"&&s.equals("1")).collect(Collectors.toList());  
        System.out.println(list2.toString());  
	}
	/*
	 * sublist为arraylist的内部类 返回的是 arraylist的视图
	 */
	@Test
	public void subList() {
		List<String> list1 = new ArrayList<String>();  
        list1.add("1");  
        list1.add("1");  
        list1.add("2");  
        list1.add("3");  
        List<String> subList = list1.subList(1, 2);
        System.out.println(subList.getClass());
        subList.add("99");
        System.out.println(Arrays.toString(list1.toArray()));
	}

}
