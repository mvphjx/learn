package com.learn.hanjx.thread.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class SimpleDateTest
{
    public void SimpleDate()
    {
        Date date = new Date();
        SimpleDateFormat sd = new SimpleDateFormat();
        sd.format(date);
        //
        formatBySimpleDateFormat(date);
        formatByDateTimeFormatter(date);

    }

    //method1
    public String formatBySimpleDateFormat(Date date)
    {
        DateFormat dateFormat = df.get();
        return dateFormat.format(date);
    }

    private final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>()
    {
        protected DateFormat initValue()
        {
            return new SimpleDateFormat("yyyy");
        }
    };

    //method2 by jdk8

    public String formatByDateTimeFormatter(Date date)
    {
        LocalDateTime localDateTime =
                LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        return format.format(localDateTime);
    }

    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy");
}
