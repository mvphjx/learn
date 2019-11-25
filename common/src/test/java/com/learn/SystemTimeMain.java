package com.learn;

import org.junit.Test;

import java.io.IOException;
import java.util.Date;

/**
 * Created by han on 2017/12/15.
 */
public class SystemTimeMain
{
    /*
    		 D:
		 cd D:\workspace\github\learn\target\test-classes\
		 java com.learn.SystemTimeMain
     */
    public static void main(String[] args)
    {
        String date = "2017-12-14";
        try
        {
            Runtime run = Runtime.getRuntime();
            String command = "cmd.exe /c date" + " " + (date);
            run.exec(command);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println(new Date());
    }

}
