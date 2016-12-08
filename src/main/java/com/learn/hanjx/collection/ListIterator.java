package com.learn.hanjx.collection;
/*
 * list遍历方式
 * 迭代器API
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListIterator {
    public static void main(String args[]){
        List<Long> lists = new ArrayList<Long>();

        for(Long i=0l;i<1000000l;i++){
            lists.add(i);
        }  
        
        Long twoOk = twoMethod(lists);
        Long threeOk = threeMethod(lists);
        
        System.out.println("Two:" + twoOk);
        System.out.println("Three:" + threeOk);
        
    }
    
    /*
     * 简便list遍历
     */
    public static Long twoMethod(List<Long> lists){
        
        Long timeStart = System.currentTimeMillis();
        for(Long string : lists)    {
            System.out.println(string);
        }
        Long timeStop = System.currentTimeMillis();

        return timeStop -timeStart ;
    }
    /*Iterator 迭代器
     * 可以remove元素
     */
    public static Long threeMethod(List<Long> lists){
        
        Long timeStart = System.currentTimeMillis();
        Iterator<Long> it = lists.iterator();
        while (it.hasNext())
        {
                System.out.println(it.next());
        }
        Long timeStop = System.currentTimeMillis();

        return timeStop -timeStart ;
    }    
 
}
