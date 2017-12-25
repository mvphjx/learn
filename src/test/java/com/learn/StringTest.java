package com.learn;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by han on 2017/11/22.
 */
public class StringTest
{
    public static void main(String[] args)
    {
        String s = "/";
        String s1 = "/11";
        String s2 = "/11/03";
        System.out.println(s.split("/").length);
        System.out.println(s1.split("/").length);
        System.out.println(s2.split("/").length);
    }
    @Test
    public void strArrays(){
        String[] ary = {"1","2","3"};
        System.out.println(Arrays.toString(ary));
    }
}
