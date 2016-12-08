package com.learn.hanjx.collection;
/**
1、HashSet底层是采用HashMap实现的。
HashSet 的实现比较简单，HashSet 的绝大部分方法都是通过调用 HashMap 的方法来实现的
，因此 HashSet 和 HashMap 两个集合在实现本质上是相同的。
2、HashMap的key就是放进HashSet中对象，value是Object类型的。
3、当调用HashSet的add方法时，实际上是向HashMap中增加了一行(key-value对)，
该行的key就是向HashSet增加的那个对象，该行的value就是一个Object类型的常量
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
 
public class ListToSet {
    public static void main(String[] args) {
        List<String> listWithDup = new ArrayList<String>();
        listWithDup.add("1");
        listWithDup.add("2");
        listWithDup.add("3");
        listWithDup.add("1");
 
        List<String> listWithoutDup = new ArrayList<String>(new HashSet<String>(listWithDup));
        System.out.println("list with dup:"+ listWithDup);
        System.out.println("list without dup:"+ listWithoutDup);
    }
}