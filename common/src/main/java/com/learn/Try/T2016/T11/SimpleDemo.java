package com.learn.Try.T2016.T11;

public class SimpleDemo {
	public static void main(String[] args) {
		int i =1;
		int j =10;
		for(;i<10;i++){
			for(j =10;j>0;j--){
				if((i&j)<1){
					System.out.println(i+"&"+j+"="+(i&j));
					//System.out.println(i+"|"+j+"="+(i|j));					
				}
			}
		}
	}

}
