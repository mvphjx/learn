package com.learn.Try.T2016.T11;

/** 
 * Created by lw on 14-5-19. 
 * <p/> 
 * Class到底怎么初始化，测试内容已打乱顺序 
 * <p/> 
 * 1.先执行静态，按照静态语句块或者静态变量的顺序执行 
 * 2.执行main 
 * 3.非静态变量的初始化，按声明顺序执行 
 * 4.构造代码块 
 * 5.构造方法 
 * ----------------- 
 * 静态代码执行几次？ 
 * 父类子类执行顺序？ 
 * 如果是父类的引用指向子类呢？ 
 * 如果成员变量中引用了其他的class呢？ 
 * 对于 int i=10; int j; 执行i=10时候 j 声明了吗？如果是静态的呢？ 
 * 
 * http://blog.csdn.net/xiaohulunb/article/details/26264841
 */  
public class InitClass extends SuperInitClass {  
  
    static {  
        System.out.println("InitClass->静态代码块 1");  
    }  
  
    static String s1 = "InitClass->静态变量s1";  
  
    static String s2 = getS2();  
  
    static {  
        System.out.println("InitClass->静态代码块 2");  
        System.out.println("InitClass->静态代码块 2->访问static String s1=" + s1);  
    }  
  
  
    String s3 = "InitClass->成员变量s3";  
    String s4 = getS4();  
    static String s5;  
  
    {  
        System.out.println("InitClass->构造方法代码块");  
        System.out.println("InitClass->构造方法代码块 执行时候成员变量s3是否已经初始化了？" + (s3 != null));  
    }  
  
    InitClass() {  
        System.out.println("InitClass->构造方法");  
        System.out.println("InitClass->构造方法 执行时候成员变量s3是否已经初始化了？" + (s3 != null));  
    }  
  
  
    static String getS2() {  
        System.out.println("InitClass->getS2()执行->初始化静态变量s1");  
        return "InitClass->初始化静态变量s1";  
    }  
  
    static String getS4() {  
        System.out.println("InitClass->getS4()执行->初始化成员变量s4");  
        return "InitClass->初始化成员变量s4";  
    }  
  
    public static void main(String[] args) {  
        System.out.println("main.........run");  
        System.out.println();  
        System.out.println("------------InitClass initClass = new InitClass();----------------执行");  
        InitClass initClass = new InitClass();  
        System.out.println();  
        System.out.println("------------SuperInitClass superInitClass = new InitClass();----------------执行");  
        SuperInitClass superInitClass = new InitClass();  
    }  
  
}  
class SuperInitClass {  
    static {  
        System.out.println("SuperInitClass->静态代码块 1");  
    }  
  
    static String s1 = "SuperInitClass->静态变量s1";  
  
    static String s2 = getS2();  
  
    static {  
        System.out.println("SuperInitClass->静态代码块 2");  
        System.out.println("SuperInitClass->静态代码块 2->访问static String s1=" + s1);  
    }  
  
    String s3 = "SuperInitClass->成员变量s3";  
    String s4 = getS4();  
  
    People people = new People();  
  
    {  
        System.out.println("SuperInitClass->构造方法代码块");  
        System.out.println("SuperInitClass->构造方法代码块 执行时候成员变量s3是否已经初始化了？" + (s3 != null));  
    }  
  
    SuperInitClass() {  
        System.out.println("SuperInitClass->构造方法");  
        System.out.println("SuperInitClass->构造方法 执行时候成员变量s3是否已经初始化了？" + (s3 != null));  
    }  
  
    static String getS2() {  
        System.out.println("SuperInitClass->getS2()执行->初始化静态变量s1");  
        return "SuperInitClass->初始化静态变量s1";  
    }  
  
    static String getS4() {  
        System.out.println("SuperInitClass->getS4()执行->初始化成员变量s4");  
        return "SuperInitClass->初始化成员变量s4";  
    }  
}  
  
class People {  
  
    People() {  
        System.out.println("PeopleClass->init...");  
    }  
}   