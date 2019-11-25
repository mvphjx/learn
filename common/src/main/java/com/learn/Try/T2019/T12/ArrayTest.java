package com.learn.Try.T2019.T12;

import java.util.HashMap;

public class ArrayTest
{
    public static HashMap<String, short[]> cfgMap = new HashMap<>();
    public static void main(String[] args)
    {
        final short[] as = cfgMap.get("a");
        System.out.println(as);
    }
}
