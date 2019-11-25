package com.learn.Try.T2017.T03;

public class ExtendPropoTest1  extends ExtendPropoTest{
	String name ;
/**
 * 
 * SEVERE: Servlet.service() for servlet [springmvc] in context with path [/ysabisweb] threw exception
 *  [Handler processing failed; nested exception is 
 *  java.lang.UnsatisfiedLinkError: no java_fpt_helper in java.library.path] with root cause
 *  java.lang.UnsatisfiedLinkError: no java_fpt_helper in java.library.path
 * @param args
 */
	public static void main(String[] args) {
		ExtendPropoTest1 ex = new ExtendPropoTest1();
		ex.name="123";
		System.out.println(ex);
		System.out.println(ex.getName());
		System.out.println(System.getProperty("YSABIS.debug"));
		try{
			System.loadLibrary("java_fpt_helper"+"");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//public	static	native	int		convToFPTCompatibleImgMnt(BspData srcImg, BspData srcMnt, BspData destImg, BspData destMnt, int option);
	
	public String getName(){
		return null;//super.name;
	}
}
