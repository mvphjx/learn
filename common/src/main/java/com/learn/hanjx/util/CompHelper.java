package com.learn.hanjx.util;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * JAVA类比较帮助类，用来快速比较
 * @author 北京北大高科指纹技术有限公司
 *         www.idfounder.com
 *         北京海鑫科金高科技股份有限公司
 *         www.hisign.com.cn
 * 创建日期：2016年10月10日下午3:20:45
 */
public class CompHelper
{
    public static boolean equals(Byte arg1, Byte arg2)
    {
        if (arg1 == arg2)
            return true;
        if (arg1 == null || arg2 == null)
            return false;
        return arg1.equals(arg2);
    }

    public static boolean equals(Short arg1, Short arg2)
    {
        if (arg1 == arg2)
            return true;
        if (arg1 == null || arg2 == null)
            return false;
        return arg1.equals(arg2);
    }

    public static boolean equals(Integer arg1, Integer arg2)
    {
        if (arg1 == arg2)
            return true;
        if (arg1 == null || arg2 == null)
            return false;
        return arg1.equals(arg2);
    }

    public static boolean equals(Long arg1, Long arg2)
    {
        if (arg1 == arg2)
            return true;
        if (arg1 == null || arg2 == null)
            return false;
        return arg1.equals(arg2);
    }

    public static boolean equals(Boolean arg1, Boolean arg2)
    {
        if (arg1 == arg2)
            return true;
        if (arg1 == null || arg2 == null)
            return false;
        return arg1.equals(arg2);
    }

    public static boolean equals(Float arg1, Float arg2)
    {
        if (arg1 == arg2)
            return true;
        if (arg1 == null || arg2 == null)
            return false;
        return arg1.equals(arg2);
    }

    public static boolean equals(Double arg1, Double arg2)
    {
        if (arg1 == arg2)
            return true;
        if (arg1 == null || arg2 == null)
            return false;
        return arg1.equals(arg2);
    }

    public static boolean equals(String arg1, String arg2)
    {
        if (arg1 == arg2)
            return true;
        if (arg1 == null || arg2 == null)
            return false;
        return arg1.equals(arg2);
    }

    public static boolean equalsIgnoreCase(String arg1, String arg2)
    {
        if (arg1 == null || arg2 == null)
        {
            if (arg1 == null && arg2 == null)
                return true;
            return false;
        }
        return arg1.equalsIgnoreCase(arg2);
    }

    public static boolean equals(Object arg1, Object arg2)
    {
        if (arg1 == arg2)
            return true;
        if (arg1 == null || arg2 == null)
            return false;
        return arg1.equals(arg2);
    }

    public static boolean equals(Timestamp arg1, Timestamp arg2)
    {
        if (arg1 == arg2)
            return true;
        if (arg1 == null || arg2 == null)
            return false;
        return arg1.equals(arg2);
    }

    public static boolean equals(Date arg1, Date arg2)
    {
        if (arg1 == arg2)
            return true;
        if (arg1 == null || arg2 == null)
            return false;
        return arg1.equals(arg2);
    }

    public static boolean equals(java.util.Date arg1, java.util.Date arg2)
    {
        if (arg1 == arg2)
            return true;
        if (arg1 == null || arg2 == null)
            return false;
        return arg1.equals(arg2);
    }

    public static int compareTo(Byte arg1, Byte arg2)
    {
        if (arg1 == null || arg2 == null)
        {
            if (arg1 == null && arg2 == null)
                return 0;
            if (arg1 == null)
                return -1;
            return 1;
        }
        return arg1.compareTo(arg2);
    }

    public static int compareTo(Short arg1, Short arg2)
    {
        if (arg1 == null || arg2 == null)
        {
            if (arg1 == null && arg2 == null)
                return 0;
            if (arg1 == null)
                return -1;
            return 1;
        }
        return arg1.compareTo(arg2);
    }

    public static int compareTo(Integer arg1, Integer arg2)
    {
        if (arg1 == null || arg2 == null)
        {
            if (arg1 == null && arg2 == null)
                return 0;
            if (arg1 == null)
                return -1;
            return 1;
        }
        return arg1.compareTo(arg2);
    }

    public static int compareTo(Long arg1, Long arg2)
    {
        if (arg1 == null || arg2 == null)
        {
            if (arg1 == null && arg2 == null)
                return 0;
            if (arg1 == null)
                return -1;
            return 1;
        }
        return arg1.compareTo(arg2);
    }

    public static int compareTo(Boolean arg1, Boolean arg2)
    {
        if (arg1 == null || arg2 == null)
        {
            if (arg1 == null && arg2 == null)
                return 0;
            if (arg1 == null)
                return -1;
            return 1;
        }
        return arg1.compareTo(arg2);
    }

    public static int compareTo(Float arg1, Float arg2)
    {
        if (arg1 == null || arg2 == null)
        {
            if (arg1 == null && arg2 == null)
                return 0;
            if (arg1 == null)
                return -1;
            return 1;
        }
        return arg1.compareTo(arg2);
    }

    public static int compareTo(Double arg1, Double arg2)
    {
        if (arg1 == null || arg2 == null)
        {
            if (arg1 == null && arg2 == null)
                return 0;
            if (arg1 == null)
                return -1;
            return 1;
        }
        // if(arg1 == arg2) return 0;
        return arg1.compareTo(arg2);
    }

    public static int compareTo(String arg1, String arg2)
    {
        if (arg1 == null || arg2 == null)
        {
            if (arg1 == null && arg2 == null)
                return 0;
            if (arg1 == null)
                return -1;
            return 1;
        }
        return arg1.compareTo(arg2);
    }

    public static int compareTo(java.util.Date arg1, java.util.Date arg2)
    {
        if (arg1 == arg2)
            return 0;
        if (arg1 == null)
            return -1;
        if (arg2 == null)
            return 1;
        return arg1.compareTo(arg2);
    }

    public static int compareTo(Date arg1, Date arg2)
    {
        if (arg1 == arg2)
            return 0;
        if (arg1 == null)
            return -1;
        if (arg2 == null)
            return 1;
        return arg1.compareTo(arg2);
    }

    public static int compareTo(Timestamp arg1, Timestamp arg2)
    {
        if (arg1 == arg2)
            return 0;
        if (arg1 == null)
            return -1;
        if (arg2 == null)
            return 1;
        return arg1.compareTo(arg2);
    }
}
