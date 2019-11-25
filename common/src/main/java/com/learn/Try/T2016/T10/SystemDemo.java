package com.learn.Try.T2016.T10;

import java.util.Arrays;

public class SystemDemo {
	public static void main(String[] args) {
		String[] strs ={"1","2","3","4","5","6","7"};
		int index =0;
		System.out.println(Arrays.toString(strs));
		int size = strs.length;
		Object oldValue = strs[index];
        int numMoved = strs.length - index - 1;
        if (numMoved > 0)
            System.arraycopy(strs, index+1, strs, index,numMoved);
        System.out.println(--size);
		System.out.println(Arrays.toString(strs));
	}
}
