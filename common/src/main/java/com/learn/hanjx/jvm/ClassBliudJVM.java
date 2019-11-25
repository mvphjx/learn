package com.learn.hanjx.jvm;

/*
 * jvm  JVM是Java Virtual Machine（Java虚拟机）
 * 
 * 
 * 我用new一个对象时jvm的工作步骤来解释。

1.在栈内存定义变量此时为初始值，定义方法。基本数据类型 int 0 。引用数据类型为null;
2. 调用父类构造方法，定义父类的属性和方法(如果子类已经重写父类的方法 这时不会被覆盖，整个过程不会发生任何覆盖的情况)。
   父类的private方法是不能被重写的，你把父类的getNum改成protected 和private结果是不一样的！！
3. 给父类的变量赋值。
4. 执行父类构造方法中其他语句（此时它自己变量已经初始化和赋值完成，貌似很合理）。
5. 给自己变量赋值(在堆内存创建对象或常量)。
6.  执行构造方法中其他语句。

按照这个规则你分析一下你的代码。就是这个结果了。

这同时能解释为什么在构造函数里super()必须写在最前。这符合jvm的处理流程。子类构造方法里只能调用一种父类构造方法。

同时也得出一个值得注意的地方，构造方法里尽量避免使用非private的方法。

还有因为泛型的原因  子类重写的
 */
public class ClassBliudJVM
{


    public static void main(String[] args)
    {
        new Zi();
    }

}

//父类
class Fu
{
    //父类构造方法
    Fu()
    {
        this.getNum();
    }

    public void getNum()
    {
        System.out.println("sdasdads");
    }
}

//子类
class Zi extends Fu
{
    private int num = 4;

    //构造函数
    public Zi()
    {
        super();//结果0

        System.out.println("子类构造函数中" + this.num);//结果4
    }

    public void getNum()
    {
        System.out.println("getNum方法" + this.num);
    }
}
