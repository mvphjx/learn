package com.learn.hanjx.design.factorymethod;

import java.util.*;

/**
 * 工厂方法
 * 凡是出现了大量的产品需要创建，并且具有共同的接口时，可以通过工厂方法模式进行创建。
 */
public class FactoryTest
{

    public static void main(String[] args)
    {
        /*静态工厂方法
         * （1）代替构造函数创建对象
         * （2）方法名比构造函数清晰
         */
        Sender sender = SendFactory.produceMail();
        sender = SendFactory.produce("mail");
        sender.Send();


        /**
         * Factory Method（工厂方法）
         作用：实现决定 实例化的类  （面向接口编程）
         * {@link Collection#iterator()}
         *
         {@link ArrayList}
         {@link AbstractList} List 接口的骨干实现
         {@link java.util.AbstractCollection} Collection 接口的骨干实现，以最大限度地减少了实现此接口所需的工作

         {@link HashMap}
         {@link AbstractMap} Map 接口的骨干实现
         */
        /**Iterator（迭代器）
         * 		 作用：将集合的迭代和集合本身分离
         JDK中体现：{@link Iterable}{@link Enumeration}接口
         * **/
        Collection<Object> list = new ArrayList<>();
        Iterator<Object> it = list.iterator();//产生ArrayList<Object>的迭代器
        it.next();
    }
}
