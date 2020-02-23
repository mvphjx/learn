package com.learn.hanjx.mind.result;

public class test
{
    public static void main(String[] args)
    {
        System.out.println(new ResultBean<>(add(1, 2)));
        System.out.println(new ResultBean<>(add(null, 2)));


    }
    public static  Integer add(Integer num1,Integer num2){
        return num1+num2;
    }
}
