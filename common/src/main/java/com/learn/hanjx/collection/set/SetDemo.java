package com.learn.hanjx.collection.set;
/**
 *
 * link 会保持顺序
 * set 会根据当前对象进行排序
 */

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class SetDemo
{
    public static void main(String[] args)
    {
        TreeSet<Object> treeSet = new TreeSet<>();
        treeSet.add("2");
        treeSet.add("1");
        treeSet.add("3");
        System.out.println(Arrays.toString(treeSet.stream().toArray()));


        LinkedHashSet<Object> linkSet = new LinkedHashSet<>();
        linkSet.add("2");
        linkSet.add("1");
        linkSet.add("3");

        System.out.println(Arrays.toString(linkSet.stream().toArray()));
    }
}
