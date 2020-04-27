package com.learn.hanjx.javase.collection;

import java.util.TreeSet;

/**
 * TreeSet的使用
 *      TreeSet添加元素的时候，会自动排序
 */
public class TreeSetDemo
{
    public static void main(String[] args)
    {
        TreeSet<Integer> sets = new TreeSet<>();
        sets.add(3);
        sets.add(2);
        sets.add(1);
        sets.add(4);
        sets.add(5);
        sets.add(6);
        System.out.println(sets.first());
        System.out.println(sets.last());
    }
}
