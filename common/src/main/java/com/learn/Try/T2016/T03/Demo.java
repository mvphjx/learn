package com.learn.Try.T2016.T03;
/*
 * 测试
 * 内存溢出
 * 栈内存堆内存
 * 
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
4  * 验证数学黑洞 用户输入一个四位数，输入变换到6174的过程 
5  * 例如：1234 
6  * 4321 - 1234 = 3087 
7  * 8730 - 0378 = 8352
8  * 8532 - 2358 = 6174
            取任意一个4位数(4个数字均为同一个数的除外)，将该数的4个数字重新组合，形成可能的最大数和可能的最小数，
            再将两者之间的差求出来;对此差值重复同样过程，最后你总是至达卡普雷卡尔黑洞6174，至达这个黑洞最多需要14个步骤。
10  */
public class Demo {
	public static  void  main(String [] args) throws Exception{
		getNumChange("0001");
		getOutOfMemory();
	}
	/**
	 * 如果输入重复四位数，无法达到递归终结条件
	 * 栈内存溢出
	 */
	private static void getNumChange(String num){
		if("6174".endsWith(num)){
			return;
		}
		int numint = Integer.valueOf(num);
		int []nums = new int[4];
		nums[0]=numint/1000;
		nums[1]=numint/100%10;
		nums[2]=numint/10%10;
		nums[3]=numint%10;
		Arrays.sort(nums);
		int newnum2 = nums[0]*1000+nums[1]*100+nums[2]*10+nums[3];
		int newnum1 = nums[3]*1000+nums[2]*100+nums[1]*10+nums[0];
		System.out.println(newnum1+"-"+newnum2 +"="+(newnum1-newnum2) );
		getNumChange(String.valueOf((newnum1-newnum2) ));
	}
	/**
	 * 一直往集合（List Map）中增加数据
	 * 测试  java.lang.OutOfMemoryError: Java heap space
	 * 堆内存溢出
	 */
	private static void getOutOfMemory(){
		Long time1  = System.currentTimeMillis();
		try{
			List  list  =  new ArrayList();
			while(true){
				list.add("取任意一个4位数(4个数字均为同一个数的除外)，将该数的4个数字重新组合，形成可能的最大数和可能的最小数， 再将两者之间的差求出来;对此差值重复同样过程，最后你总是至达卡普雷卡尔黑洞6174，至达这个黑洞最多需要14个步骤");
			}
		}catch(Exception e){
            e.printStackTrace();
		}finally{
			System.out.println("所用时间（毫秒）："+(System.currentTimeMillis()-time1));
		}
		
	}

}
