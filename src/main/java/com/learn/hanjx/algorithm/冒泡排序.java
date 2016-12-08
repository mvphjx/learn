package com.learn.hanjx.algorithm;

import java.util.Arrays;

public class 冒泡排序 {
	public static void main(String [] s){
		int [] ary = {1,3,5,7,4,2,0};
		int length = ary.length;
		for(int i =0;i<length;i++){
			for(int j =0;j<length-i-1;j++){
				System.out.println("now compare :"+ j+" and "+(j+1));
				if(ary[j]<ary[j+1]){
					int swap = ary[j];
					ary[j]=ary[j+1];
					ary[j+1]= swap;			
				}
			}
		}
		System.out.println(Arrays.toString(ary));
		sort(ary);
		System.out.println(Arrays.toString(ary));
	}
	 public static void sort(int[] a)
	    {
	        int temp = 0;
	        for (int i = a.length - 1; i > 0; --i)
	        {
	            for (int j = 0; j < i; ++j)
	            {
	                if (a[j + 1] < a[j])
	                {
	                    temp = a[j];
	                    a[j] = a[j + 1];
	                    a[j + 1] = temp;
	                }
	            }
	        }
	    }

}
