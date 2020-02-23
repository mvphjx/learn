package com.learn.hanjx.concurrent.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class SimpleDateTest
{
    public static void main(String[] args)
    {
        Date date = new Date();
        SimpleDateFormat sd = new SimpleDateFormat();
        sd.format(date);
        //
        SimpleDateTest dateTest = new SimpleDateTest();
        System.out.println(dateTest.formatBySimpleDateFormat(date));
        System.out.println(dateTest.formatByDateTimeFormatter(date));
    }

    //method1  线程不安全，线程并发访问优化
    private final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>()
    {
        protected DateFormat initialValue()
        {
            return new SimpleDateFormat("yyyy");
        }
    };

    public String formatBySimpleDateFormat(Date date)
    {
        DateFormat dateFormat = df.get();
        return dateFormat.format(date);
    }

    //method2 by jdk8

    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy");

    public String formatByDateTimeFormatter(Date date)
    {
        LocalDateTime localDateTime =
                LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        return format.format(localDateTime);
    }
}
