package com.learn.hanjx.aop.springaop;

import java.lang.reflect.Method;

/**
 *顾问接口类.
 */
public interface Advisor {   

    /**
     *所做的操作.
     */
    public void doInAdvisor(Object proxy, Method method, Object[] args);   
}
