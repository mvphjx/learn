package com.learn.Try.T2019;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DateTest
{
    /**
     * 日期范围计算工具
     * @param time   yyyyMMddHHmmss
     * @param isBegin  是否计算开始时间
     * @return
     */
    public static String getTime(String time, boolean isBegin)
    {
        int year = 1970, month = 1, dayOfMonth = 1, hour = 0, minute = 0, second = 0;
        int length = time.length();
        if (length >= 4)
        {
            year = Integer.valueOf(time.substring(0, 4));

        }
        if (length >= 6)
        {
            month = Integer.valueOf(time.substring(4, 6));

        }
        if (length >= 8)
        {
            dayOfMonth = Integer.valueOf(time.substring(6, 8));

        }
        if (length >= 10)
        {
            hour = Integer.valueOf(time.substring(6, 8));
        }
        if (length >= 12)
        {
            minute = Integer.valueOf(time.substring(6, 8));
        }
        if (length >= 14)
        {
            return time;
        }
        LocalDateTime localDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute, second);
        if (!isBegin)
        {
            switch (length)
            {
                case 12:
                    localDateTime=localDateTime.plusMinutes(1);
                    break;
                case 10:
                    localDateTime=localDateTime.plusHours(1);
                    break;
                case 8:
                    localDateTime=localDateTime.plusDays(1);
                    break;
                case 6:
                    localDateTime=localDateTime.plusMonths(1);
                    break;
                case 4:
                    localDateTime=localDateTime.plusYears(1);
                    break;
            }
        }
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return localDateTime.format(format);
    }

    public static void main(String[] args)
    {
        List<String> testValue = new ArrayList();
        testValue.add("2019");
        testValue.add("201902");
        testValue.add("20190201");
        testValue.add("2019020301");
        testValue.add("201902030102");
        testValue.add("20190203010209");
        for (String s : testValue)
        {
            System.out.println("开始时间："+DateTest.getTime(s, true));
            System.out.println("结束时间："+DateTest.getTime(s, false));
        }


    }

}
