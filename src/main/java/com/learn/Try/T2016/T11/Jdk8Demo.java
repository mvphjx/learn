package com.learn.Try.T2016.T11;
/**
 * jdk8 新特性
 */
import java.time.Clock;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.Test;

public class Jdk8Demo {
	public static void main(String[] args) {
		
	}
	/*
	 * java.time.*
	 */
	@Test
	public  void time_demo(){
		Clock clock = Clock.systemDefaultZone();
		System.out.println(clock.millis());	
	}
	/*
	 * Map接口增加  扩展方法    
	 * default  method
	 */
	@Test
	public  void map_demo(){
		Map<Integer, String> map = new HashMap<>();	 
		for (int i = 0; i < 10; i++) {
		    map.putIfAbsent(i, "val" + i);
		} 
		//Lambda 表达式
		map.forEach((id, val) -> System.out.println(val));
	}
	/*
	 * “函数式接口”是指仅仅只包含一个抽象方法的接口，每一个该类型的lambda表达式都会被匹配到这个抽象方法。
	 * 因为 默认方法 不算抽象方法，所以你也可以给你的函数式接口添加默认方法。
	 */
	@FunctionalInterface
	interface Converter<F, T> {
		///只包含一个抽象方法的接口
	    T convert(F from);
	    default void demo(){
	    	System.out.println("default void demo()");
	    }
	}
	@Test
	public  void functionInterface_demo(){
		//Lambda  用法总结
		Converter<String, Integer> converter = agrs -> Integer.valueOf(agrs);
		Integer converted = converter.convert("123");
		System.out.println(converted+1);    // 124
		//::  对象引用传递
		Converter<String, Integer> conver = Integer::valueOf;
		System.out.println("Lambda:"+conver.getClass());
		System.out.println("Lambda 调用方法 return :"+conver.convert("0").getClass());
	}
	/*
	 * 比较匿名内部类
	 * 与
	 * Lambda
	 */
	@FunctionalInterface
	interface  Person { 
	    public abstract void eat(); 
	    default void demo(){
	    	System.out.println("default void demo()");
	    	eat();
	    }
	} 
	@FunctionalInterface
	interface  Person_Param { 
	    public abstract String eat(Long num); 
	    default void demo(){
	    	System.out.println("default void demo()");
	    	eat(888L);
	    }
	} 
	@Test
	public  void innerclass_demo() throws Exception{
		Person person = new Person() { 
            public void eat() { 
                System.out.println(this.getClass()+" eat something"); 
            } 
        }; 
        System.out.println("匿名内部类:"+person.getClass());
        person.eat();
        /*   构建匿名对象
         *   参数 -> 方法体(注意返回值类型)
         */
        Person person_lambda =()->{
        	System.out.println(this.getClass()+" eat something");
        	//无法调用 defaul方法!!!!!!
        	//demo();  
        };
        person_lambda.demo();
        
        Person_Param person_lambda2 = num->{System.out.println("eat"+num);return null;};
        person_lambda2.demo();
        
        Callable<Runnable> call  = ()->()->{System.out.println(this.getClass()+" run ");};
        call.call().run();
        
        Runnable run  = ()->{System.out.println("public abstract void run();");};
        run.run();
           
	}
	
	
	/*
	 * 访问接口的默认方法
	 * 函数式接口
	 * java.util.function.*
	 */
	@Test
	public void utilFunction_demo(){
		/*
		 * Predicate接口
		 * 该接口包含多种默认方法来将Predicate组合成其他复杂的逻辑
		 */
		System.out.println("\nPredicate接口\n");
		Predicate<String> predicate = (s) -> s.length() > 0;
		//验证String长度是不是>0
		System.out.println("Predicate test length>0:"+predicate.test("string"));
		/*
		 * Function接口
		 * 附带了一些可以和其他函数组合的默认方法（compose, andThen）
		 */
		System.out.println("\nFunction接口\n");
		Function<String, Integer> toInteger = Integer::valueOf;
		Function<String, String> backToString = toInteger.andThen(String::valueOf);
		//
		System.out.println(backToString.apply("123").getClass());     // "123" 
		/*
		 * Consumer 接口
		 * void accept(Object)
		 */
		System.out.println("\nConsumer 接口\n");
		/*
		 * 语法     类名::方法名
		 *     参数->方法体
		 */
		java.util.function.Consumer<Date> consumer1 = System.out::println;
		java.util.function.Consumer<Date> consumer11 = param->System.out.println(param);
		Consumer<Date> obj = consumer1.andThen(String::valueOf);
		consumer1.accept(new Date());
		consumer11.accept(new Date());
		java.util.function.Consumer<String> consumer2 = a->{System.out.println(a);};
		consumer2.accept("aaa");
		/*
		 * 综合应用 接口
		 * 
		 */
		System.out.println("\n综合应用 接口\n");
		List<String> stringCollection = new ArrayList<>();
		stringCollection.add("ddd2"); 
		stringCollection.add("aaa2");
		stringCollection.add("bbb1");
		stringCollection.add("aaa1");
		stringCollection.add("bbb3");
		stringCollection.add("ccc");
		stringCollection.add("bbb2");
		stringCollection.add("ddd1");
		stringCollection
	    .stream()
	    .sorted()
	    .filter((s) -> s.startsWith("a")) 
	    .forEach(System.out::println);		
		stringCollection.removeIf(string->"ccc".equals(string));
		stringCollection.forEach(System.out::println);
		stringCollection.forEach(Object::hashCode);
	}
		
	
	

}
