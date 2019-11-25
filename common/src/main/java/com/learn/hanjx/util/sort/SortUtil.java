package com.learn.hanjx.util.sort;
/**
 * 排序的工具类
 */
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class SortUtil {
	
	/**
	 * 工具函数 对Map排序
	 * @param map
	 */
	public static void SortMap(Map<String, Object> map){
		// 排序函数
        TreeMap<String, Object> tm = new TreeMap<String, Object>(
        		new Comparator<String>(){
					@Override
					public int compare(String o1, String o2) {
						return o1.compareTo(o2);
					}
        		}
        );
        tm.putAll(map);
        //map 排序结束
        map = tm;
	}

}
