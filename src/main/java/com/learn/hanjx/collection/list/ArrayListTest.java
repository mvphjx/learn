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
	public void ForEachTest() {
		List<String> list = new ArrayList<>();
		list.add("0");
		list.add("1");
		try {
			for (String s : list) {
				if (s.equals("1")) {
					// ConcurrentModificationException
					list.remove(s);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
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
					list2.remove(s);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
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
}
