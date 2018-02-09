package com.learn.hanjx.util.log;

public class StackTraceUtil
{
    public static String getMethod(){
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }
}
