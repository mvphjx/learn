package com.learn.Try.T2017.T02;
/**验证
 * integer -128 ~ 127  Integer.Catch生成，其他复制堆中生成
 * @author han
 *
 */
public class IntegerCatchTest {
	
	public static void main(String[] args) {
		Integer i10  = 10;
		Integer i11  = Integer.valueOf("10");
		Integer i12  =1+9;
		System.out.println(i10==10);
		System.out.println(i10==i11);
		System.out.println(i10==i12);
		
		Integer i2  = 1033;
		Integer i3  = Integer.valueOf("1033");
		Integer i4  =10+1023;
		System.out.println(i2==1033);
		System.out.println(i2==i3);
		System.out.println(i2==i4);
	}
}
