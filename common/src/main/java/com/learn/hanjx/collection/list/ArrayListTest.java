package com.learn.hanjx.collection.list;

/**
 * 一些需要注意的集合用法
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class ArrayListTest {
	public static void main(String[] args) {

	}
	/*
	 * AsList 获取的是 数组包装类，很多方法没有实现。
	 */
	@Test
	public void AsListTest() {
		String[] strs = { "1", "a", "D" };
		List<String> list = Arrays.asList(strs);
		System.out.println(list.get(0));
		strs[0] = "0";
		// if String Arrays change,List change.
		System.out.println(list.get(0));
		// java.lang.UnsupportedOperationException
		list.add("asd");
	}

	@Test
	public void ForEachRemoveTest() {
		List<String> list = new ArrayList<>();
		list.add("0");
		list.add("1");
		try {
			for (String s : list) {
				if (s.equals("1")) {
					list.remove(s);
				}
			}
			System.out.println("for (String s : list)...  remove : success");
		} catch (Exception e) {
			/**
			 * Returns the cause of this throwable or {@code null} if the
			 * cause is nonexistent or unknown.  (The cause is the throwable that
			 * caused this throwable to get thrown.)
			 */
			System.out.println("for (String s : list)...  remove :"+e.getCause());//null
			System.out.println("for (String s : list)...  remove :"+e.getClass());
		}
		System.out.println(list);
		
		List<String> list2 = new ArrayList<>();
		list2.add("0");
		list2.add("1");
		Iterator<String> it = list2.iterator();
		try {
			while (it.hasNext()) {
				/*
				  java.util.ConcurrentModificationException
					at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:886)
				 */
				String s = it.next();
				if ("1".equals(s)) {
					it.remove();
				}
			}
			System.out.println("Iterator<String> it...  remove : success");
		} catch (Exception e) {
			
			System.out.println("Iterator<String> it...   remove :"+e.getCause());
		}
		System.out.println(list2);

	}
	@Test
	public void ListSizeTest() {
		String[] strs = { "1", "a", "D" };
		List<String> list = Arrays.asList(strs);
		if(list.size()>0){
			//java.lang.UnsupportedOperationException
			Object obj = list.get(0);
			list.remove(obj);
			System.out.println(list.toArray().toString());
		}
	}
	public List<Long> ids1 = new ArrayList<>();
	public List<Long> ids2 = new ArrayList<>();
	{
		ids1.add(1L);
		ids1.add(5L);
		ids1.add(10L);
		ids1.add(20L);
		ids1.add(25L);
		ids2.add(1L);
		ids2.add(4L);
		ids2.add(8L);
		ids2.add(12L);
		ids2.add(16L);
		ids2.add(20L);
	}
	@Test
	//交集
	public void ListRetainTest() {
		ids1.retainAll(ids2);
		System.out.println(ids1);
	}
}
