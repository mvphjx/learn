package com.learn.Try.T2016.T03;

import java.util.Arrays;

/*
 * 快速排序
 */
public class T20160307 {
	public static void sort(int arr[], int low, int high) {
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
			System.out.println("  "+Arrays.toString(arr));
			while (l < h && arr[l] <= povit)
				l++;
			if (l < h) {
				int temp = arr[h];
				arr[h] = arr[l];
				arr[l] = temp;
				h--;
			}
			System.out.println(Arrays.toString(arr));
		}
		System.out.println("l=" + (l + 1) + " h=" + (h + 1) + " povit=" + povit);
		if (l > low)
			sort(arr, low, l - 1);
		if (h < high)
			sort(arr, l + 1, high);
	}
	public static void main(String [] args){
		int []  arr = {2,5,22,8,7,55,1,9};
		sort(arr,0,arr.length-1);
	}

}
