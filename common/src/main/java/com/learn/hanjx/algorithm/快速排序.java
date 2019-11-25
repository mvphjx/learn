package com.learn.hanjx.algorithm;
/**
1 从数列中挑出一个元素，称为 “基准”（pivot），

2 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作。

3 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
 */
import java.util.Arrays;

public class 快速排序 {
	
	public static void main(String []s){
		int [] ary = {5,3,6,7,4,2};
		int [] arr = {5,3,6,7,4,2};
		sort(ary,0,ary.length-1);
		System.out.println("-------sortplus----------------------------------------");
		sortplus(arr,0,arr.length-1);
	}
	public static void sortplus(int arr[], int low, int high) {
		System.out.println("start-l=" + (low) + " h=" + (high) + " povit=" + arr[low]);
		int l = low;
		int h = high;
		int povit = arr[low];
		while (l < h) {
			while (l < h && arr[h] >= povit)
				h--;
			if (l < h) {
				int temp = arr[h];
				arr[h] = arr[l];
				arr[l] = temp;
				l++;
			}
			System.out.println("  "+Arrays.toString(arr)+"  l="+l+"  h="+h);
			while (l < h && arr[l] <= povit)
				l++;
			if (l < h) {
				int temp = arr[h];
				arr[h] = arr[l];
				arr[l] = temp;
				h--;
			}
			System.out.println(Arrays.toString(arr)+"  l="+l+"  h="+h);
		}
		System.out.println("l=" + (l + 1) + " h=" + (h + 1) + " povit=" + povit);
		if (l > low)
			sort(arr, low, l - 1);
		if (h < high)
			sort(arr, l + 1, high);
	}
	public static void sort(int arr[],int low,int high){
			
		int l=low;
		int h=high;
		int povit=arr[low];//哨兵
		 
		while(l<h)
		{
			while(l<h&&arr[h]>=povit)
				h--;
			if(l<h){
				int temp=arr[h];
				arr[h]=arr[l];
				arr[l]=temp;
				l++;
			}
		 
			while(l<h&&arr[l]<=povit)
				l++;
		 
			if(l<h){
				int temp=arr[h];
				arr[h]=arr[l];
				arr[l]=temp;
				h--;
			}
		}
		print(arr);
		System.out.print("l="+(l+1)+" h="+(h+1)+" povit="+povit+"\n");
		if(l>low) sort(arr,low,l-1);
		if(h<high) sort(arr,l+1,high);
	}

	private static void print(int[] arr) {
		System.out.println(Arrays.toString(arr));
		
	}
		 
		

}
