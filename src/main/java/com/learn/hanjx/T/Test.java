package com.learn.hanjx.T;
/**
 * 一个例子全部说明java泛型中的
 * K,V,T,E,?,object的意思及其用法
 * K表示键，V表示值，T表示type类型，E表示enum枚举，其实这四个都只是符号，都是表示泛型名称,
 * 下面的例子的T全部可以换成E，也可以换成K,V,hanjx，都没关系。
 */

import com.learn.hanjx.util.log.StackTraceUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * T1,T2都是随便定义的东西;
 * 注意1:他们不会关联到其他类，只是在本类中通用，只是告诉我们new的时候要加入泛型
 *
 * @param <T1>
 * @param <T2>
 */
public class Test<T1, T2>
{
    private Class<T1> t1;
    private Class<T2> t2;

    public Test()
    {
        System.out.println("new的时候要加入泛型 T1 T2:" + Arrays.toString(this.getClass().getTypeParameters()));
    }

    public Test(Class<T1> t1, Class<T2> t2)
    {
        System.out.println("new的时候要加入泛型 T1 T2:" + Arrays.toString(this.getClass().getTypeParameters()));
        this.t1 = t1;
        this.t2 = t2;
    }

    public static void main(String[] args)
    {
        Test<Integer, String> test = new Test<>();
        test.getaa();
        test.getbb("");
        test.getbb(1);
        test.getcc(Test.class);
        test.initFruit(new RedApple());
        //注意下6:面这个HashMap的括号里面不能是T,E,T1,T2等不确定的东西,但可以是?  
        HashMap<Object, String> map = new HashMap<Object, String>();
        HashMap<?, ?> map2 = new HashMap<Object, String>();
        List<?> list = new ArrayList<String>();
        //factory
        Product<Integer> intProduct = new Product(new ProductFactory());
    }

    public T2 getaa()
    {
        Object obj = new Object();
        System.out.println(" 类内部 get实例类型" + ((T1) obj).getClass());
        System.out.println(" 类内部 get实例类型" + ((T2) obj).getClass());
        //注意2:T2将自动转型为String,这个不需要去担心  
        return (T2) "few";

    }

    public <T1> void getbb(T1 a)
    {
        System.out.println(StackTraceUtil.getMethod() + ":" + a.getClass());
    }

    public <T> Class<?> getcc(Class<T> a)
    {
        //getcc前面的Class<T>前面缺少<T>将编译错误,注意4:Class<?>里面的问号可以换成T  
        System.out.println("getcc:" + a.getClass().getName());
        //注意5:参数里面的Class<T>最大的好处是如果方法里面定义了泛型，可以自动获取类型值，比如如下的List<T>可以自动获取到a的类型，不必强调死  
        List<T> aa = new ArrayList<T>();
        //System.out.println("getcc:"+aa.get(0));
        return a;
    }

    /**
     * @param a   限制a必须为-子类
     * @param <T> 限制a必须为-子类
     */
    public <T extends Fruit> void initFruit(T a)
    {
        System.out.println(StackTraceUtil.getMethod() + ":" + a.getClass());


        /**
         * PECS原则
         *  如果要从集合中读取类型T的数据，并且不能写入，可以使用 ? extends 通配符；(Producer Extends)
         如果要从集合中写入类型T的数据，并且不需要读取，可以使用 ? super 通配符；(Consumer Super)
         如果既要存又要取，那么就不要使用任何通配符。
         */
        List<? super Apple> listSuper = new ArrayList<>();
        listSuper.add(new Apple());
        Object object = listSuper.get(0);//no read
        List<? extends Apple> listExtends = new ArrayList<>();
        listExtends.add(null);//no wirte
        Apple apple = listExtends.get(0);
    }
}

class Fruit
{
}

class Apple extends Fruit
{
}

class RedApple extends Apple
{
}

class Orange extends Fruit
{
}

/**
 * 工厂模式创建泛型
 *
 * @param <T>
 */
interface Factory<T>
{
    T create();
}

class Product<T>
{
    public <F extends Factory<T>> Product(F factory)
    {
        factory.create();
    }
}

class ProductFactory implements Factory<Integer>
{
    public Integer create()
    {
        Integer integer = new Integer(10);
        System.out.println(integer);
        return integer;
    }
}