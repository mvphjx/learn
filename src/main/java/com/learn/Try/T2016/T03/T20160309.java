package com.learn.Try.T2016.T03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
public class T20160309 {
	public static  void  main(String [] args) throws Exception{
		List  list  =  new ArrayList();
		int i = 10 ;
		while (i>0){
			list.add(i);
			i--;
		}
		Collections.sort(list);
		Collections.sort(list, new Comparator<Object>() {
			@Override
			public int compare(Object o1, Object o2) {
				if(1>2){
					return 1;
				}
				return 0;
			}	
		} );
	}
	/**
	 * 重载
	 * @param i
	 * @param j
	 */
	public  void fun (int i ,long j){
		//测试枚举类型
		Color s =Color.BLANK;
	}
	public  void fun (int i ,int j){
		/*Singleton（单例）
		 * 作用：保证类只有一个实例；提供一个全局访问点
			JDK中体现：（1）Runtime
					（2）NumberFormat
		 */ 
		//java.text.NumberFormat.
		java.lang.Runtime.getRuntime();
		/*静态工厂模式  根据参数创建对象
			（1）代替构造函数创建对象
			（2）方法名比构造函数清晰
		JDK中体现：
			（1）Integer.valueOf
			（2）Class.forName
		 */ 
		java.util.Calendar.getInstance(null, null);
		/*Factory Method（工厂方法） 
				作用：子类决定哪一个类实例化
				JDK中体现：Collection.iterator方法
		ArrayList extends AbstractList
		List 接口的骨干实现 abstract class>
		AbstractList extends  AbstractCollection implements List
		此类提供 Collection 接口的骨干实现，以最大限度地减少了实现此接口所需的工作>
		AbstractCollection  implements Collection	//产生迭代器	
		
		HashMap extends AbstractMap
		AbstractMap implements Map
		*/
		java.util.Collection list  = new java.util.ArrayList();
		java.util.Iterator it =list.iterator();
		/*抽象工厂 
		作用：创建某一种类的对象
		JDK中体现：
		（1）java.sql包
		（2）UIManager（swing外观）
		*/
		/**包装类模式**/ 
		java.lang.Integer.parseInt(null);
	}
	public  void fun (int i ,float j){
		
	}
	/**
	 * 枚举
	 * @author hanjianxiang
	 *
	 */
	public enum Color {  
		  RED, GREEN, BLANK, YELLOW  
	} 
}
