package com.learn;

import org.junit.Test;

import java.util.Date;

/**
 * Created by han on 2017/12/8.
 *
 * 如何更改java系统时间
 */
public class DateTest
{
    @Test
    public void getTime(){
        System.out.println(System.currentTimeMillis());
        System.out.println(new Date());
    }
}
