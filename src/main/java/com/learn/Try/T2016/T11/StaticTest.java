package com.learn.Try.T2016.T11;
/**
 * 加载->验证->准备->解析->初始化->使用->卸载
 * 
 * @author han
 *
 */
public class StaticTest {
    
    public static void main(String[] args){
                 
        staticFunection();           
    }
     /**
      * 调用静态方法（类方法）   会依次  顺序加载静态方法（类方法）
      * 静态代码块 / 非静态代码块 /静态成员变量 
      */
    static StaticTest st = new StaticTest();
     
    static{
        System.out.println("1");
    }
     
    {
        System.out.println("2");
    }
     
    public StaticTest() {
        System.out.println("3");
        System.out.println("a ="+ a +", b="+b);
    }
     
    public static void staticFunection(){
        System.out.println("4");
    }
     
    int a = 110;
    static int b = 112;
}
