package com.learn;

import org.junit.Test;

import java.io.IOException;
import java.util.Date;

/**
 * Created by han on 2017/12/15.
 */
public class SystemTimeTest
{
    //修改windows7日期
    @Test
    public void modifyDate()
    {
        String date = "2017-12-05";
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

    @Test
    public void modifyDate2()
    {
//Operating system name
        String osName = System.getProperty("os.name");
        String cmd = "";
        try
        {
            if (osName.matches("^(?i)Windows.*$"))
            {// Window 系统
                // 格式 HH:mm:ss
                cmd = "  cmd /c time 22:35:00";
                Process process=Runtime.getRuntime().exec(cmd);
                process.destroy();
            } else
            {// Linux 系统
                // 格式：yyyyMMdd
                cmd = "  date -s 20090326";
                Runtime.getRuntime().exec(cmd);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
