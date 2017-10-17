package com.learn.Try.T2017.T09.classload;

/**
 * Created by han on 2017/9/25.
 * 一个需要被加密的Java Class
 */
public class MyClassBase implements MyClassInterface {
    public void say() {
        System.out.append("Hello World!");
    }
}