package com.learn.hanjx.aop.jdkproxy;

import java.lang.reflect.Method;

/**
 *顾问接口接口
 *切面逻辑（advise） 
 */
public interface Advisor {   

    /**
     *所做的操作.
     */
    public void doInAdvisor(Object proxy, Method method, Object[] args);   
}
