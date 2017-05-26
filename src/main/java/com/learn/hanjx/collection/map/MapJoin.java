package com.learn.hanjx.collection.map;

import java.util.HashMap;

public class MapJoin {
public static void main(String[] args) {
	HashMap map=new HashMap();
	map.put("1", "A");
	HashMap map1 = new HashMap();
	map1.put("2", "B");
	map1.put("1", "C");
	map.putAll(map1);
	System.out.println(map);
}
}
