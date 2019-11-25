package com.learn.Try.T2017.T08;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by han on 2017/8/14.
 */
public class DateParseTest
{
    public static void main(String[] args)
    {
        String time = "20170814130355";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date parsedDate = null;
        try
        {
            parsedDate = dateFormat.parse(time);
        } catch (ParseException e)
        {
            e.printStackTrace();
            try
            {
                dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
                parsedDate = dateFormat.parse(time);
                System.out.println(parsedDate);
            } catch (ParseException e1)
            {
                e1.printStackTrace();
            }
        }
    }
}
