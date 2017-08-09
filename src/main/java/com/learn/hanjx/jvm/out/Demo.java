package com.learn.hanjx.jvm.out;
/*
 * http://blog.csdn.net/mvphjx/article/details/50723562
 * 测试
 * 内存溢出
 * 栈内存堆内存
 * 
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Demo {
	public static  void  main(String [] args) throws Exception{
		//getNumChange("0001");
		getOutOfMemory();
	}
	/**
	 * 如果，无法达到递归终结条件
	 * 创建太多变量导致
	 * 栈内存溢出
	 */
	private static void getNumChange(String num){
		if("6174".endsWith(num)){
			return;
		}
		int numint = Integer.valueOf(num);
		getNumChange(String.valueOf(numint));
	}
	/**
	 * 一直往集合（List Map）中增加数据
	 * 测试  java.lang.OutOfMemoryError: Java heap space
	 * 堆内存溢出
	 * -Xmx40m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=E:\Java\dump  
	 * 记录堆信息  方便报错查看 -Xmx40m 可选
	 */
	private static void getOutOfMemory(){
		Long time1  = System.currentTimeMillis();
		List<String>  list  =  new ArrayList<String>();
		//查看java内存信息
		//System.gc();
	    Runtime imp = Runtime.getRuntime();
	    //System.out.println("最大内存:"+imp.maxMemory()/1024);
		Long allMemory = imp.maxMemory()/1024;
		Long freeMemory = imp.freeMemory()/1024;
		System.out.println("空闲内存:"+freeMemory+"KB*****剩余百分比:"+(freeMemory*100/allMemory));
		try{		
			while(true){
				imp = Runtime.getRuntime();
				if(freeMemory>imp.freeMemory()/1024){
					freeMemory=imp.freeMemory()/1024;
					System.out.println("空闲内存:"+freeMemory+"KB*****剩余百分比:"+(freeMemory*100/allMemory));
				}
				list.add("取任意一个4位数(4个数字均为同一个数的除外)，将该数的4个数字重新组合，形成可能的最大数和可能的最小数， 再将两者之间的差求出来;对此差值重复同样过程，最后你总是至达卡普雷卡尔黑洞6174，至达这个黑洞最多需要14个步骤");
			}
		}catch(Exception e){
            e.printStackTrace();
		}finally{
			System.out.println("所用时间（毫秒）："+(System.currentTimeMillis()-time1));
		}
		
	}

}
