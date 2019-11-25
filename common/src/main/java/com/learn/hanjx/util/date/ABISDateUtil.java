package com.learn.hanjx.util.date;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.learn.hanjx.test.ABISHelper;

/**
 * 
 * @author 北京北大高科指纹技术有限公司
 *         www.idfounder.com
 *         北京海鑫科金高科技股份有限公司
 *         www.hisign.com.cn
 * 创建日期：2016年10月10日下午3:20:45
 */
public class ABISDateUtil
{
    static private final Logger LOG = LoggerFactory.getLogger(ABISDateUtil.class);
    public static final String yyyy__MM__dd_HH_mm_ss = "yyyy/MM/dd HH:mm:ss";
    public static final String yyyy__MM__dd_HH_mm = "yyyy/MM/dd HH:mm";
    public static final String yyyy__MM__dd_HH = "yyyy/MM/dd HH";
    public static final String yyyy__MM__dd = "yyyy/MM/dd";
    public static final String yyyy__MM = "yyyy/MM";

    public static final String yy__MM__dd_HH_mm_ss = "yy/MM/dd HH:mm:ss";
    public static final String yy__MM__dd_HH_mm = "yy/MM/dd HH:mm";
    public static final String yy__MM__dd_HH = "yy/MM/dd HH";
    public static final String yy__MM__dd = "yy/MM/dd";
    public static final String yy__MM = "yy/MM";

    public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
    public static final String yyyy_MM_dd_HH = "yyyy-MM-dd HH";
    public static final String yyyy_MM_dd = "yyyy-MM-dd";
    public static final String yyyy_MM = "yyyy-MM";

    public static final String yy_MM_dd_HH_mm_ss = "yy-MM-dd HH:mm:ss";
    public static final String yy_MM_dd_HH_mm = "yy-MM-dd HH:mm";
    public static final String yy_MM_dd_HH = "yy-MM-dd HH";
    public static final String yy_MM_dd = "yy-MM-dd";
    public static final String yy_MM = "yy-MM";

    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public static final String yyyyMMddHHmmssSS = "yyyyMMddHHmmssSS";
    public static final String yyyyMMddHHmm = "yyyyMMddHHmm";
    public static final String yyyyMMddHH = "yyyyMMddHH";
    public static final String yyyyMMdd = "yyyyMMdd";
    public static final String yyyyMM = "yyyyMM";

    public static final String yyyy = "yyyy";
    public static final String yy = "yy";
    public static final String MM = "MM";

    public static final String CN_yyyy_MM_dd_HH_mm_ss = "yyyy年MM月dd日HH点mm分ss秒";
    public static final String CN_yyyy_MM_dd_HH_mm = "yyyy年MM月dd日HH点mm分";
    public static final String CN_yyyy_MM_dd_HH = "yyyy年MM月dd日HH点";
    public static final String CN_yyyy_MM_dd = "yyyy年MM月dd日";
    public static final String CN_yyyy_MM = "yyyy年MM月";
    public static final String CN_yyyy = "yyyy年";

    private static List<String> format;

    static
    {
        format = new ArrayList<String>();
        format.add(yyyy__MM__dd_HH_mm_ss);
        format.add(yy__MM__dd_HH_mm_ss);
        format.add(yyyy_MM_dd_HH_mm_ss);
        format.add(yy_MM_dd_HH_mm_ss);
        format.add(CN_yyyy_MM_dd_HH_mm_ss);

        format.add(yyyy__MM__dd_HH_mm);
        format.add(yy__MM__dd_HH_mm);
        format.add(yyyy_MM_dd_HH_mm);
        format.add(yy_MM_dd_HH_mm);
        format.add(CN_yyyy_MM_dd_HH_mm);

        format.add(yyyy__MM__dd_HH);
        format.add(yy__MM__dd_HH);
        format.add(yyyy_MM_dd_HH);
        format.add(yy_MM_dd_HH);
        format.add(CN_yyyy_MM_dd_HH);

        format.add(yyyy__MM__dd);
        format.add(yyyy_MM_dd);
        format.add(yyyy_MM_dd);
        format.add(yy_MM_dd);
        format.add(CN_yyyy_MM_dd);

        format.add(yyyy__MM);
        format.add(yy__MM);
        format.add(yyyy_MM);
        format.add(yy_MM);
        format.add(CN_yyyy_MM);

        format.add(yyyyMMddHHmmss);
        format.add(yyyyMMddHHmmssSS);
        format.add(yyyyMMddHHmm);
        format.add(yyyyMMddHH);
        format.add(yyyyMMdd);
        format.add(yyyyMM);
    }

    /**
     * 这个函数格式化日期检索语句中的第二个日期(因为第二个日期是做 "<" 或 “BETWEEN AND" 也就是说要包含输入的日期)
     * @param date
     * @return
     */
    public static String fmtSecondDate(String date)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss);
        try
        {
            String fmtDate = date;
            int len = date.length();
            if (len % 2 != 0 || len < 4)
                return null;
            GregorianCalendar gc = new GregorianCalendar();
            java.util.Date d = null;
            int field;
            String fmtStr;
            switch (len)
            {
            case 6:
                synchronized (dateFormat)
                {
                    dateFormat.applyPattern(ABISDateUtil.yyyyMM);
                    d = dateFormat.parse(date);
                    field = Calendar.MONTH;
                    fmtStr = ABISDateUtil.yyyyMM;
                }
                break;
            case 8:
                synchronized (dateFormat)
                {
                    dateFormat.applyPattern(ABISDateUtil.yyyyMMdd);
                    d = dateFormat.parse(date);
                    field = Calendar.DATE;
                    fmtStr = ABISDateUtil.yyyyMMdd;
                }
                break;
            case 10:
                synchronized (dateFormat)
                {
                    dateFormat.applyPattern(ABISDateUtil.yyyyMMddHH);
                    d = dateFormat.parse(date);
                    field = Calendar.HOUR;
                    fmtStr = ABISDateUtil.yyyyMMddHH;
                }
                break;
            case 12:
                synchronized (dateFormat)
                {
                    dateFormat.applyPattern(ABISDateUtil.yyyyMMddHHmm);
                    d = dateFormat.parse(date);
                    field = Calendar.MINUTE;
                    fmtStr = ABISDateUtil.yyyyMMddHHmm;
                }
                break;
            default:
                return fmtDate;
            }

            gc.setTimeInMillis(d.getTime());
            gc.add(field, 1);
            d = gc.getTime();
            fmtDate = ABISDateUtil.format(d, fmtStr);
            return fmtDate;
        }
        catch (Exception e)
        {
            LOG.error("fmtSecondDate throw exception '{}'", e.getMessage());
            LOG.debug("stack trace :", e);
        }
        return null;
    }


    /**
     * 时间部位名称
     */
    public static class DateField
    {
        public static final int Year = GregorianCalendar.YEAR;
        public static final int MONTH = GregorianCalendar.MONTH;
        public static final int DATE = GregorianCalendar.DATE;
        public static final int HOUR = GregorianCalendar.HOUR;
        public static final int MINUTE = GregorianCalendar.MINUTE;
        public static final int SECOND = GregorianCalendar.SECOND;
    }

    public static String format(java.util.Date date, String inputFmt)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss);
        if (date == null)
            return null;
        if (ABISHelper.isEmpty(inputFmt))
            return null;
        String str = null;
        synchronized (dateFormat)
        {
            dateFormat.applyPattern(inputFmt);
            str = dateFormat.format(date);
        }
        return str;

    }

    /**
     * 将一个日期字符串根据制定根式进行格式化
     * 
     * @param dateStr
     * @return
     */
    public static String format(String dateStr, String inputFmt)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss);
        if (ABISHelper.isEmpty(dateStr))
            return null;
        if (ABISHelper.isEmpty(inputFmt))
            return null;
        String fmtStr = null;
        synchronized (dateFormat)
        {
            dateFormat.applyPattern(inputFmt);
            Date date = ABISDateUtil.toJavaDate(dateStr);
            if (date == null)
                return null;
            fmtStr = dateFormat.format(date);
        }
        return fmtStr;
    }

    /**
     * 将一个时间字符串转换另一种格式的时间字符串
     * 
     * @param dateStr
     * @param inputFmt
     * @param outputFmt
     * @return
     */
    public static String formatDateStr(String dateStr, String inputFmt, String outputFmt)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss);
        if (ABISHelper.isEmpty(dateStr))
            return null;
        if (ABISHelper.isEmpty(inputFmt))
            return null;
        String str = null;
        try
        {
            java.util.Date date = null;
            synchronized (dateFormat)
            {
                dateFormat.applyPattern(inputFmt);
                date = dateFormat.parse(dateStr);
            }
            str = ABISDateUtil.format(date, outputFmt);
        }
        catch (Exception e)
        {
            return null;
        }
        return str;
    }

    /**
     * 将一个日期字符串格式化
     */
    public static String format(String dateStr)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss);
        String fmtStr;
        synchronized (dateFormat)
        {
            dateFormat.applyPattern(CN_yyyy_MM_dd_HH_mm);
            Date date = ABISDateUtil.toJavaDate(dateStr);
            if (date == null)
                return null;
            fmtStr = dateFormat.format(date);
        }
        return fmtStr;
    }

    /**
     * 将一个JAVA中的Date类型格式化
     */
    public static String format(java.util.Date date)
    {
        return format(date, yyyy_MM_dd_HH_mm_ss);
    }

    /**
     * 将一个时间戳格式化
     */
    public static String format(long dateTime)
    {
        return format(dateTime, yyyy_MM_dd_HH_mm_ss);
    }

    /**
     * 将一个时间戳按照指定的格式进行格式化
     */
    public static String format(long dateTime, String inputFmt)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss);
        if (ABISHelper.isEmpty(inputFmt))
            return null;
        String str = null;
        synchronized (dateFormat)
        {
            dateFormat.applyPattern(inputFmt);
            str = dateFormat.format(dateTime);
        }
        return str;
    }

    /**
     * 转换为GregorianCalendar
     * 
     * @return GregorianCalendar
     */
    public static GregorianCalendar toGregorianCalendar(java.sql.Timestamp t)
    {
        GregorianCalendar g = new GregorianCalendar();
        g.setTimeInMillis(t.getTime());
        return g;
    }

    /**
     * 将一个日期字符串转换成JAVA中的日期对象
     * 
     * @param dateStr 格式:yyyyMMdd
     * @return java.util.Date
     */
    public static java.util.Date toJavaDate(String dateStr)
    {
        return toJavaDate(dateStr, yyyyMMdd);
    }

    public static java.util.Date toJavaDate(String dateStr, String inputFmt)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss);
        if (ABISHelper.isEmpty(dateStr))
            return null;
        if (ABISHelper.isEmpty(inputFmt))
            return null;
        try
        {
            java.util.Date date;
            synchronized (dateFormat)
            {
                dateFormat.applyPattern(inputFmt);
                date = dateFormat.parse(dateStr);
            }
            return date;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
     * 将一个日期字符串转换成SQL的日期对象
     * 
     * @param dateStr 格式:yyyyMMdd
     */
    public static java.sql.Date toSQLDate(String dateStr)
    {
        return toSQLDate(dateStr, yyyyMMdd);
    }

    /**
     * 将一个日期字符串转换成SQL的日期对象
     * 
     * @param dateStr
     * @param inputFmt dateStr的格式
     * @return java.sql.Date
     */
    public static java.sql.Date toSQLDate(String dateStr, String inputFmt)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss);
        if (ABISHelper.isEmpty(dateStr))
            return null;
        if (ABISHelper.isEmpty(inputFmt))
            return null;
        try
        {
            java.sql.Date d;
            synchronized (dateFormat)
            {
                dateFormat.applyPattern(inputFmt);
                java.util.Date date = dateFormat.parse(dateStr);
                if (date == null)
                    return null;
                d = new java.sql.Date(date.getTime());
            }
            return d;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
     * 将日期字符串转换成java.sql.Timestamp对象
     * 
     * @param dateStr 日期字符串(字符串默认格式为yyyyMMdd)
     * @return java.sql.Timestamp
     */
    public static java.sql.Timestamp toSQLTimestamp(String dateStr)
    {
        for (String f : format)
        {
            Timestamp t = toSQLTimestamp(dateStr, f);
            if (t != null)
            {
                System.out.println("格式:" + f);
                return t;
            }
        }
        return null;
    }

    /**
     * 将日期字符串转换成java.sql.Timestamp对象
     * 
     * @param dateStr 日期字符串
     * @param inputFmt 格式化字符串
     * @return java.sql.Timestamp
     */
    public static java.sql.Timestamp toSQLTimestamp(String dateStr, String inputFmt)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss);
        if (ABISHelper.isEmpty(dateStr))
            return null;
        if (ABISHelper.isEmpty(inputFmt))
            return null;
        try
        {
            java.util.Date date = null;
            synchronized (dateFormat)
            {
                dateFormat.applyPattern(inputFmt);
                date = dateFormat.parse(dateStr);
            }
            if (date == null)
                return null;
            Timestamp stamp = new Timestamp(date.getTime());
            return stamp;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
     * 将时间字符串加上一定的时间
     * 
     * @param timestamp 时间对象
     * @param dateField 被加上时间的单位 {@link com.hisign.pu.comm.base.util.ABISDateUtil.DateField}
     * @param amount 被加上的时间
     * @return java.sql.Timestamp
     */
    public static java.sql.Timestamp addSQLTimestamp(java.sql.Timestamp timestamp, int dateField, int amount)
    {
        try
        {
            if (timestamp == null)
                return null;
            GregorianCalendar g = new GregorianCalendar();
            g.setTimeInMillis(timestamp.getTime());
            g.add(dateField, amount);
            Timestamp newTime = new Timestamp(g.getTime().getTime());
            return newTime;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
     * 将时间字符串加上一定的时间
     * 
     * @param dateStr 时间字符串
     * @param inputFmt 时间字符串的格式
     * @param dateField 被加上时间的单位 {@link com.hisign.pu.comm.base.util.ABISDateUtil.DateField}
     * @param amount 被加上的时间
     * @return java.sql.Timestamp
     */
    public static java.sql.Timestamp addSQLTimestamp(String dateStr, String inputFmt, int dateField, int amount)
    {
        try
        {
            Timestamp t = toSQLTimestamp(dateStr, inputFmt);
            if (t == null)
                return null;
            return addSQLTimestamp(t, dateField, amount);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
     * 验证一个字符串日期是否有效
     */
    public static boolean validataDate(String date)
    {
        try
        {
            int len = date.length();
            if (len % 2 != 0 || len < 4)
                return false;
            int year = Integer.parseInt(date.substring(0, 4));
            int month = 1;
            if (len > 4)
            {
                month = Integer.parseInt(date.substring(4, 6));
                if (month < 1 || month > 12)
                    return false;
            }
            if (len > 6)
            {
                int day = Integer.parseInt(date.substring(6, 8));
                switch (month)
                {
                case 2:
                    if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
                    {
                        if (day > 29)
                            return false;
                    }
                    else
                    {
                        if (day > 28)
                            return false;
                    }
                    break;
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    if (day > 31)
                        return false;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    if (day > 30)
                        return false;
                    break;
                }
            }
            if (len > 8)
            {
                int hour = Integer.parseInt(date.substring(8, 10));
                if (hour > 24)
                    return false;
            }
            if (len > 10)
            {
                int minute = Integer.parseInt(date.substring(10, 12));
                if (minute > 59)
                    return false;
            }
            if (len > 12)
            {
                int second = Integer.parseInt(date.substring(12, 14));
                if (second > 59)
                    return false;
            }

        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }

    public static String formatBySqlDate(java.sql.Date sqlDate)
    {
        return formatBySqlDate(sqlDate, yyyyMMdd);
    }

    /**
     * 将SQL的日期对象格式化
     * 
     * @param sqlDate
     */
    public static String formatBySqlDate(java.sql.Date sqlDate, String fmt)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss);
        if (sqlDate == null)
            return null;
        String dateStr = null;
        synchronized (dateFormat)
        {
            dateFormat.applyPattern(fmt);
            dateStr = dateFormat.format(new Date(sqlDate.getTime()));
        }
        return dateStr;
    }

    /**
     * 格式化SQL中的时间对象
     */
    public static String formatBySqlTime(java.sql.Timestamp stamp)
    {
        return formatBySqlTime(stamp, yyyyMMdd);
    }

    /**
     * 格式化SQL中的时间对象
     * 
     * @param stamp
     */
    public static String formatBySqlTime(java.sql.Timestamp stamp, String fmt)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss);
        if (stamp == null)
            return null;
        String dateStr = null;
        synchronized (dateFormat)
        {
            dateFormat.applyPattern(fmt);
            dateStr = dateFormat.format(new Date(stamp.getTime()));
        }
        return dateStr;
    }

    /**
     * 获取SQL中的当前时间对象
     */
    public static java.sql.Timestamp getCurrentTimeStamp()
    {
        Timestamp stamp = new Timestamp(new java.util.Date().getTime());
        return stamp;
    }

    /**
     * 获取SQL中的当前日期对象
     */
    public static java.sql.Date getCurrentSqlDate()
    {
        java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
        return sqlDate;

    }

    /**
     * 获取今天的日期字符串
     */
    public static String getToday()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss);
        GregorianCalendar gc = new GregorianCalendar();
        String dateStr = null;
        synchronized (dateFormat)
        {
            dateFormat.applyPattern(yyyyMMdd);
            dateStr = dateFormat.format(gc.getTime());
        }
        return dateStr;
    }

    /**
     * 获取当前时间(yyyy-MM-dd HH:mm:ss)
     * 
     * @return String
     */
    public static String getCurrentTime()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss);
        GregorianCalendar gc = new GregorianCalendar();
        String dateStr;
        synchronized (dateFormat)
        {
            dateFormat.applyPattern(yyyy_MM_dd_HH_mm_ss);
            dateStr = dateFormat.format(gc.getTime());
        }
        return dateStr;
    }

    /**
     * 获取制定格式的当前时间
     * 
     * @return String
     */
    public static String getCurrentTime(String format)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss);
        GregorianCalendar gc = new GregorianCalendar();
        String dateStr;
        synchronized (dateFormat)
        {
            dateFormat.applyPattern(format);
            dateStr = dateFormat.format(gc.getTime());
        }
        return dateStr;
    }

    /**
     * 将SQL中的时间对象转换为Java中的日期对象
     * 
     * @param stamp
     * @return java.sql.Date
     */
    @SuppressWarnings("deprecation")
    public static java.sql.Date toSQLDate(java.sql.Timestamp stamp)
    {
        if (stamp == null)
            return null;
        java.sql.Date d = new java.sql.Date(stamp.getYear(), stamp.getMonth(), stamp.getDate());
        return d;
    }

    /**
     * 将秒转换为时间字符串格式
     * 
     * @param second 秒
     * @return String
     */
    public static String formatTime(long secondTime)
    {

        int hour = (int) (secondTime / 3600);
        if (hour > 0)
        {
            secondTime = (int) (secondTime % 3600);
        }
        int minute = (int) (secondTime / 60);
        if (minute > 0)
        {
            secondTime = (int) (secondTime % 60);
        }
        int second = (int) secondTime;
        StringBuffer sb = new StringBuffer();
        if (hour != 0)
        {
            if (hour < 10)
            {
                sb.append("0");
            }
            sb.append(hour);
            sb.append(":");
        }
        if (minute < 10)
        {
            sb.append("0");
        }
        sb.append(minute);
        sb.append(":");
        if (second < 10)
        {
            sb.append("0");
        }
        sb.append(second);
        return sb.toString();
    }
    public static void main(String[] args)
    {
    	format("2017-05-10 00:00:00");

        String s = "20130529";
        // Timestamp t = toSQLTimestamp(s);
        // System.out.println(t);

        String e1 = "^(" + "(\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?" + "((((0?[13578])|(1[02]))[\\-\\/\\s]?"
                + "((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
                + "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?" + "((0?[1-9])|([1-2][0-9])))))"
                + "|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?" + "((((0?[13578])|(1[02]))[\\-\\/\\s]?"
                + "((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-9]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9]))))"
                + ")?$";
        String e2 = "^(" + "((([02468][048])|([13579][26]))[\\-\\/\\s]?" + "((((0?[13578])|(1[02]))[\\-\\/\\s]?"
                + "((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
                + "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?" + "((0?[1-9])|([1-2][0-9])))))"
                + "|((([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?" + "((((0?[13578])|(1[02]))[\\-\\/\\s]?"
                + "((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-9]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9]))))"
                + ")?$";
        Pattern p = Pattern.compile(e2);
        Matcher m = p.matcher(s);
        boolean b = m.matches();
        System.out.println(b);
    }

}
