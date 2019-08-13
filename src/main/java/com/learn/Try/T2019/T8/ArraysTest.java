package com.learn.Try.T2019.T8;

import java.util.Arrays;

/**
 * 利用泛型、可变参数  动态生成数组
 */
public class ArraysTest
{
    public static void main(String[] args)
    {
        String[] result = ArraysTest.getArrays("1", "2");
        System.out.println(Arrays.toString(result));
        String[] ts = ArraysTest.makeArrays("21", "22");
        System.out.println(Arrays.toString(ts));
    }

    public static String[] getArrays(String... s)
    {
        return s;
    }

    /**
     * 返回数组
     * @param args  可变参数
     * @param <T>  尖括号用来描述返回值类型  T[]表示此类型的数组
     * @return
     */
    public static <T> T[] makeArrays(T... args) {
        return args;
    }
}
