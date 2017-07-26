package com.learn.hanjx.util;

import com.learn.hanjx.exceptionAndError.exception.HanException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.naming.NamingException;
import java.io.*;
import java.net.URLDecoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 系统的一些工具类
 */
public class HanHelper
{
    static private final Logger LOG = LoggerFactory.getLogger(HanHelper.class);
    /**
     * 判断给定的列表是否包含数据
     * 
     * @param list
     *        列表
     * @return
     */
    public static boolean isEmpty(Collection<?> list)
    {
        return list == null || list.isEmpty();
    }

    public static boolean isEmpty(Object obj)
    {
        return obj == null || obj.toString().isEmpty();
    }

    public static <K, V> boolean isEmpty(Map<K, V> map)
    {
        return map == null || map.isEmpty();
    }

    public static <T> boolean isEmpty(T[] array)
    {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(long[] array)
    {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(int[] array)
    {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(short[] array)
    {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(byte[] array)
    {
        return array == null || array.length == 0;
    }

    /**
     * 判断给定的字符串是否包含数据
     * 
     * @param str
     * @return
     */
    public static boolean isEmpty(String str)
    {
        return str == null || str.isEmpty();
    }

    /**
     * 判断给定的字符串是否是数字
     * 
     * @param str
     * @return
     */
    public static boolean isDigit(String str)
    {
        if (isEmpty(str))
            return false;

        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c))
                return false;
        }

        return true;
    }

    public static boolean isLetterOrDigit(String str)
    {
        if (isEmpty(str))
            return false;

        for (char c : str.toCharArray())
        {
            if (!Character.isLetterOrDigit(c))
                return false;
        }

        return true;
    }

    public static boolean isASCII(String str)
    {
        if (isEmpty(str))
            return false;

        for (char c : str.toCharArray())
        {
            if ((byte) c < 0)
                return false;
        }

        return true;
    }

    /**
     * 判断给定的字符串是否全部都是给定的字符
     * 
     * @param str
     *        带判断的字符串
     * @param c
     *        待判定的字符
     * @return 全部都是返回true，否则返回false
     */
    public static boolean isAllChars(String str, char c)
    {
        if (isEmpty(str))
            return false;

        for (char c1 : str.toCharArray())
        {
            if (c1 != c)
                return false;
        }
        return true;
    }

    /**
     * 使用给定的子串拆分给定的字符串
     * 
     * @param str
     *        待拆分的字符串
     * @param split
     *        分隔符，可以是正则表达式
     * @return 拆分后的子串列表
     */
    public static String[] split(String str, String split)
    {
        if (HanHelper.isEmpty(str) || HanHelper.isEmpty(split))
            return null;

        return str.split(split);
    }

    public static String[] split(String str, char split)
    {
        return split(str, String.valueOf(split));
    }

    /**
     * 把字符串拆分为Number列表
     * 
     * @param str
     *        待拆分的字符串
     * @param split
     *        分隔符
     * @return Long列表
     */
    public static <T extends Number> List<T> split(String str, String split, Class<T> c)
    {
        String[] items = split(str, split);
        if (items == null || items.length < 1)
            return null;

        List<T> list = new ArrayList<T>();
        for (String item : items)
        {
            try
            {
                T num = (T) c.getConstructor(String.class).newInstance(item.trim());
                list.add(num);
            }
            catch (Exception e)
            {
                throw new HanException(e);
            }
        }
        return list;
    }

    /**
     * 创建指定长度的指定字符填充的字符串
     * 
     * @param length
     *        字符串的长度
     * @param c
     *        要填充的字符
     * @return 创建好的字符串，长度为length， 内容全部为字符c
     */
    public static String createString(int length, char c)
    {
        if (length < 1)
            return new String();

        char[] chars = new char[length];

        Arrays.fill(chars, c);

        return new String(chars);
    }

    /**
     * 把平时用*作为通配符的字符串转换为符合正则表达式格式的字符串，如37*=>^37\w*
     * 
     * @param wildString
     *        平时用*作为通配符的字符串
     * @return 符合正则表达式格式的字符串
     */
    public static String toRegex(String wildString)
    {
        if (isEmpty(wildString))
            return null;

        StringBuffer sb = new StringBuffer();

        if (wildString.charAt(0) != '*')
            sb.append("^");

        // 转换字符串中使用的正则表达式字符，如.+之类
        wildString = wildString.replaceAll("\\.", "\\\\.");
        wildString = wildString.replaceAll("\\+", "\\\\+");
        wildString = wildString.replaceAll("\\*+", "\\\\w*");
        wildString = wildString.replaceAll("\\?", "\\\\w");
        sb.append(wildString);

        if (wildString.charAt(wildString.length() - 1) != '*')
            sb.append("$");

        return sb.toString();
    }

    /**
     * 把平时用*作为通配符的字符串转换为符合正则表达式格式的字符串，如37*=>^37\w*
     * 
     * @param wildStringList
     *        平时用*作为通配符的字符串列表
     * @return 符合正则表达式格式的字符串列表
     */
    public static List<String> toRegex(Collection<String> wildStringList)
    {
        if (wildStringList == null)
            return null;

        List<String> regexList = new ArrayList<String>();

        for (String wildString : wildStringList)
        {
            regexList.add(toRegex(wildString));
        }

        return regexList;
    }

    /**
     * 把平时用*作为通配符的字符串转换为符合正则表达式格式的字符串，如37*=>^37\w*
     * 
     * @param wildStringList
     *        平时用*作为通配符的字符串数组
     * @return 符合正则表达式格式的字符串列表
     */
    public static List<String> toRegex(String[] wildStringList)
    {
        if (wildStringList == null)
            return null;

        List<String> regexList = new ArrayList<String>();

        for (String wildString : wildStringList)
        {
            if (wildString == null)
                continue;
            regexList.add(toRegex(wildString));
        }

        return regexList;
    }

    /**
     * 判断给定的字符串是否匹配给定表达式
     * 
     * @param regexList
     *        正则表达式列表
     * @param str
     *        要判定的字符串
     * @return
     */
    public static boolean matches(Collection<String> regexList, String str)
    {
        if (isEmpty(regexList))
            return false;

        for (String regex : regexList)
        {
            if (str.matches(regex))
                return true;
        }

        return false;
    }

    public static boolean matches(String[] regexList, String str)
    {
        if (isEmpty(regexList))
            return false;

        for (String regex : regexList)
        {
            if (str.matches(regex))
                return true;
        }

        return false;
    }

    /**
     * 获取异常的详细信息
     * 
     * @param e
     *        异常
     * @return
     */
    public static String getExceptionStackTrace(Throwable e)
    {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    public static boolean isConnectionFail(Exception e)
    {
        if (e instanceof IllegalStateException || e instanceof NamingException
                || e.getCause() instanceof IllegalStateException || e.getCause() instanceof NamingException)
        {
            return true;
        }
        return false;
    }

    /**
     * 获取异常的详细信息
     * 
     * @param e
     *        异常
     * @return
     */
    public static String toString(Throwable e)
    {
        return getExceptionStackTrace(e);
    }

    public static byte[] toBytes(Throwable e)
    {
        String str = getExceptionStackTrace(e);

        try
        {
            return str.getBytes("utf-8");
        }
        catch (Exception e1)
        {
            throw new HanException(e1);
        }
    }

    /**
     * 把数字number转换为长度为length的字符串，如果位数不够的话在前面用'0'填充
     * 
     * @param number
     *        待转换的数字
     * @param length
     *        转换后的字符串的长度
     * @return
     */
    public static String toString(int number, int length)
    {
        String format = String.format("%%0%dd", length);

        return String.format(format, number);
    }

    public static String toString(Object obj)
    {
        return (obj == null) ? null : obj.toString();
    }

    public static String formatString(Object obj)
    {
        return obj == null ? "" : obj.toString();
    }

    /**
     * 把对象转换为数字
     * 
     * @param obj
     *        待转换的对象
     * @param klass
     *        数字对象类
     * @return
     */
    public static <T> T parseNumber(Object obj, Class<T> klass)
    {
        if (HanHelper.isEmpty(obj))
            return null;
        try
        {
            return klass.getConstructor(String.class).newInstance(obj.toString());
        }
        catch (Exception e)
        {
            throw new HanException(e);
        }
    }

    public static long parseLong(Object obj)
    {
        if (obj == null)
            return 0;
        try
        {
            return Long.parseLong(obj.toString());
        }
        catch (Exception e)
        {
            return 0;
        }
    }

    /**
     * 将对象转换为short类型
     * 
     * @param obj
     * @return short
     */
    public static short parseShort(Object obj)
    {
        if (obj == null)
            return 0;
        try
        {
            short s = Short.parseShort(obj.toString());
            return s;
        }
        catch (Exception e)
        {
            return 0;
        }
    }

    public static byte parseByte(Object obj)
    {
        if (obj == null)
            return 0;
        try
        {
            return Byte.parseByte(obj.toString());
        }
        catch (Exception e)
        {
            return 0;
        }
    }

    public static byte parseByte(Object obj, byte defaultValue)
    {
        if (obj == null)
            return defaultValue;
        try
        {
            return Byte.parseByte(obj.toString());
        }
        catch (Exception e)
        {
            return defaultValue;
        }
    }

    public static int parseInt(Object obj)
    {
        if (obj == null)
            return 0;
        try
        {
            return Integer.parseInt(obj.toString());
        }
        catch (Exception e)
        {
            return 0;
        }
    }

    /**
     * 把对象转换为Boolean类型
     * 
     * @param obj
     *        待转换的对象
     * @return
     */
    public static Boolean parseBoolean(Object obj)
    {
        return obj == null ? null : Boolean.valueOf(obj.toString());
    }

    /**
     * 把数字对象转换为Boolean类型
     * 
     * @param obj
     *        数字对象
     * @return
     */
    public static Boolean numberToBoolean(Object obj)
    {
        return parseNumber(obj, Long.class) != 0;
    }

    /**
     * 将布尔对象转换为字符串对象
     * 
     * @param bool
     * @return String
     */
    public static String booleanToString(Boolean bool)
    {
        if (bool == null)
            return "0";
        if (bool == false)
            return "0";
        return "1";
    }

    /**
     * 将布尔对象转换为整数(0|1)
     * 
     * @param bool
     * @return String
     */
    public static int booleanToNumber(Boolean bool)
    {
        if (bool == null)
            return 0;
        if (bool == false)
            return 0;
        return 1;
    }

    /**
     * 把字符串中的子串转换为数字，如果转换失败则使用缺省值
     * 
     * @param str
     *        字符串
     * @param beginIndex
     *        子串的起始位置
     * @param endIndex
     *        子串的结束位置
     * @param def
     *        缺省值，如果转换失败则使用该值
     * @return 转换后的数字
     */
    public static long toNumber(String str, int beginIndex, int endIndex, long def)
    {
        try
        {
            String s = str.substring(beginIndex, endIndex);
            return Long.parseLong(s.trim());
        }
        catch (Exception e)
        {
            return def;
        }
    }

    /**
     * 把字符串中的子串转换为数字，如果转换失败返回缺省值
     * 
     * @param str
     *        字符串
     * @param def
     *        缺省值，如果转换失败则使用该值
     * @return 字符串中的子串对应的数字，如果转换失败返回缺省值
     */
    public static long toNumber(String str, long def)
    {
        try
        {
            return Long.parseLong(str.trim());
        }
        catch (Exception e)
        {
            return def;
        }
    }

    /**
     * 将对象转换为布尔类型
     * 
     * @param obj
     *        数字对象或者字符串对象
     * @return boolean
     */
    public static boolean toBoolean(Object obj)
    {
        try
        {
            if (obj != null)
            {
                long l = Long.parseLong(obj.toString());
                if (l != 0)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        catch (Exception e)
        {
            try
            {
                boolean b = Boolean.valueOf(obj.toString());
                return b;
            }
            catch (Exception ex)
            {

            }
            return false;
        }
    }

    /**
     * 关闭输入流
     * 
     * @param is
     */
    public static void close(InputStream is)
    {
        if (is != null)
        {
            try
            {
                is.close();
            }
            catch (IOException e)
            {
            }
        }
    }

    /**
     * 关闭输出流
     * 
     * @param os
     */
    public static void close(OutputStream os)
    {
        if (os != null)
        {
            try
            {
                os.close();
            }
            catch (IOException e)
            {
            }
        }
    }

    /**
     * 把数据写入到给定的文件中
     * 
     * @param fileName
     *        待写入的文件名称
     * @param data
     *        待写入的数据
     * @throws IOException
     */
    public static void saveDataToFile(String fileName, byte[] data)
    {
        try (FileOutputStream os = new FileOutputStream(fileName))
        {
            // 保存数据
            os.write(data);
        }
        catch (FileNotFoundException e)
        {
            // 文件不存在
            throw new HanException(e);
        }
        catch (IOException e)
        {
            throw new HanException(e);
        }
    }

    /**
     * 把列表合并为字符串
     * 
     * @param list
     *        带合并的列表
     * @param split
     *        分隔符
     * @return 合并后的字符串
     */
    public static String combine(Collection<?> list, String split)
    {
        if (HanHelper.isEmpty(list))
            return null;

        StringBuffer sb = new StringBuffer();
        for (Object item : list)
        {
            sb.append(item.toString());
            sb.append(split);
        }
        return sb.substring(0, sb.length() - 1);
    }

    /**
     * 判断给定的值是否在列表中存在
     * 
     * @param list
     *        列表
     * @param value
     *        值
     * @return
     */
    public static boolean contains(Collection<?> list, Object value)
    {
        if (HanHelper.isEmpty(list))
            return false;

        for (Object item : list)
        {
            if (CompHelper.equals(item, value))
                return true;
        }
        return false;
    }

    /**
     * 判断给定的值是否在列表中存在
     * 
     * @param list
     *        列表
     * @param t
     *        值
     * @return
     */
    public static <T extends Number> boolean contains(Collection<T> list, T t)
    {
        if (HanHelper.isEmpty(list))
            return false;

        for (T item : list)
        {
            if (item.longValue() == t.longValue())
                return true;
        }
        return false;
    }

    /**
     * 使用带*、?的通配串进行正则表达式匹配
     * 
     * @param wild
     * @param str
     * @return
     */
    public static boolean wildMatch(String wild, String str)
    {
        // 转换为正则表达式
        wild = toRegex(wild);

        // 正则表达式匹配
        return str.matches(wild);
    }

    /**
     * 判断给定字符串中是否全部都是给定的字符
     */
    public static boolean isAll(String src, char c)
    {
        if (HanHelper.isEmpty(src))
            return false;

        for (int i = 0; i < src.length(); ++i)
        {
            if (src.charAt(i) != c)
                return false;
        }

        return true;
    }

    /**
     * 从输入流中读取数据
     * 
     * @param is
     *        输入流
     * @return 从输入流中读取的数据
     * @throws IOException
     */
    public static byte[] read(InputStream is) throws IOException
    {
        return read(is, 0);
    }

    /**
     * 从输入流中读取指定长度的数据
     * 
     * @param is
     *        输入流
     * @param size
     *        带读取数据的最大长度， 如果为0，则表示读取所有的数据
     * @return 从输入流中读取到的数据
     * @throws IOException
     */
    public static byte[] read(InputStream is, int size) throws IOException
    {
        // 字节数组输出流，用来临时保存从输入流中读取的数据
        ByteArrayOutputStream os = new ByteArrayOutputStream(size);

        // 从输入流中读取数据并写入到输出流中
        write(is, os, size);

        // 把输出流转换为字节数组
        return os.toByteArray();
    }

    /**
     * 判断给定的数字选项中是否设置了给定的掩码
     * 
     * @param option
     *        选项
     * @param mask
     *        待验证的掩码
     * @return 设置返回true
     */
    public static boolean hasOption(long option, long mask)
    {
        return (option & mask) != 0;
    }

    public static boolean hasOption(int option, int mask)
    {
        return (option & mask) != 0;
    }

    /**
     * 创建比对分区的pidMask
     * 
     * @param usedMask
     *        已经使用的掩码
     * @return 新的比对分区的掩码
     */
    public static long createPidMask(long usedMask)
    {
        long pidMask = 1;
        for (int i = 0; i < 64; ++i, pidMask <<= 1)
        {
            if (!hasOption(usedMask, pidMask))
                return pidMask;
        }

        return pidMask;
    }

    public static String format(String fmtStr, Object... obj)
    {
        if (HanHelper.isEmpty(fmtStr))
        {
            return null;
        }
        if (HanHelper.isEmpty(obj))
        {
            return fmtStr;
        }
        String rex = "\\{[0-9]+\\}";
        Pattern p = Pattern.compile(rex);
        Matcher matcher = p.matcher(fmtStr);
        String result = fmtStr;
        while (matcher.find())
        {
            int start = matcher.start();
            int end = matcher.end();
            String fmtCell = fmtStr.substring(start, end);
            int index = Integer.parseInt(fmtCell.substring(1, fmtCell.length() - 1));
            if (index >= obj.length)
                continue;
            String value = obj[index] != null ? obj[index].toString() : "";
            result = result.replace("{" + index + "}", value);
        }
        return result;
    }

    public static String format(String fmtStr, List<Object> objList)
    {
        if (HanHelper.isEmpty(objList))
        {
            return fmtStr;
        }
        return format(fmtStr, objList.toArray());
    }

    /**
     * 把输入流中的数据直接写入到输出流中
     * 
     * @param is
     *        输入流
     * @param os
     *        输出流
     * @return 输入流的长度
     * @throws IOException
     */
    public static int write(InputStream is, OutputStream os) throws IOException
    {
        byte[] buffer = new byte[4096]; // 4k的读取缓冲区
        int bytesRead; // 每次读取数据的长度
        int length = 0; // 读取数据的总长度

        while ((bytesRead = is.read(buffer)) != -1)
        {
            os.write(buffer, 0, bytesRead);

            length += bytesRead;
        }

        // 读取数据的长度
        return length;
    }

    /**
     * 从输入流中读取指定长度的数据
     * 
     * @param is
     *        输入流
     * @param os
     *        输出流
     * @param size
     *        带读取数据的最大长度， 如果为0，则表示读取所有的数据
     * @return 从输入流中读取到的数据
     * @throws IOException
     */
    public static int write(InputStream is, OutputStream os, int size) throws IOException
    {
        if (size < 1)
            return write(is, os);

        byte[] buffer = new byte[4096]; // 读取缓冲区
        int bytesRead; // 每次读取数据的长度
        int length = 0; // 读取数据的总长度

        do
        {
            // 从输入流中的读取数据
            bytesRead = Math.min(size - length, buffer.length);
            bytesRead = is.read(buffer, 0, bytesRead);
            if (bytesRead > 0)
            {
                // 把输入流中读取的数据写入到输出流中
                os.write(buffer, 0, bytesRead);
                length += bytesRead;
            }
        } while (bytesRead >= 0 && length < size);

        // 返回读取数据的长度
        return length;
    }

    /**
     * 把数组转换为集合
     * 
     * @param array
     *        [IN]数组
     * @param list
     *        [OUT]集合
     */
    public static <T> void convertArrayToCollection(T[] array, Collection<T> list)
    {
        if (HanHelper.isEmpty(array) || list == null)
            return;

        for (T item : array)
        {
            if (item != null)
                list.add(item);
        }
    }

    /**
     * 把对象数组转换为对象列表
     * 
     * @param array
     * @return
     */
    public static <T> List<T> convertArrayToList(T[] array)
    {
        if (HanHelper.isEmpty(array))
            return null;

        List<T> list = new ArrayList<T>();
        convertArrayToCollection(array, list);
        return list;
    }

    /**
     * 把集合中的数据转存到long数组中
     * 
     * @param list
     *        集合
     * @return 转换后的long[]
     */
    public static long[] createLongArray(Collection<? extends Number> list)
    {
        if (HanHelper.isEmpty(list))
            return null;

        int i = 0;
        long[] array = new long[list.size()];
        for (Number n : list)
        {
            array[i++] = n.longValue();
        }

        return array;
    }

    /**
     * Thread.sleep，不抛出异常
     * 
     * @param milliSeconds
     *        毫秒
     * @return true：成功；false：发生了异常
     */
    public static boolean sleepNoException(long milliSeconds)
    {
        try
        {
            Thread.sleep(milliSeconds);
            return true;
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt(); 
            return false;
        }
    }

    /**
     * 从输入流中读取数据
     * 
     * @param dh
     *        输入流
     * @return 从输入流中读取的数据
     * @throws IOException
     */
    public static byte[] read(DataHandler dh) throws IOException
    {
        return dh != null ? read(dh.getInputStream()) : null;
    }

    /**
     * 限定值必须在指定的区间内
     * 
     * @param minValue
     *        最小值
     * @param maxValue
     *        最大值
     * @param curValue
     *        当前值
     * @return
     */
    public static long limit(long minValue, long maxValue, long curValue)
    {
        if (curValue < minValue)
            return minValue;
        if (curValue > maxValue)
            return maxValue;
        return curValue;
    }

    /**
     * 删除给定的文件
     * 
     * @param fileName
     *        文件
     * @return
     */
    public static boolean deleteFile(String fileName)
    {
        return new File(fileName).delete();
    }

    /**
     * 字符串处理
     * 
     * @param str
     * @return
     */
    public static String replaceBlank(String str)
    {
        String dest = "";
        if (str != null)
        {
            Pattern p = Pattern.compile("\t|\r|\n");// \\s*|
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    public static int getChineseLength(String str)
    {
        String regexStr = "[\u4E00-\u9FA5]";
        return str.replaceAll(regexStr, "  ").length();
    }

    public static int length(byte[] data)
    {
        return data == null ? 0 : data.length;
    }

    public static int length(Collection<?> list)
    {
        return list == null ? 0 : list.size();
    }

    public static int length(String str)
    {
        return str == null ? 0 : str.length();
    }

    public static <T> int length(T[] arrays)
    {
        return arrays == null ? 0 : arrays.length;
    }

    /**
     * 获取数字的长度
     * 
     * @param num
     * @return
     */
    public static int length(long num)
    {
        return String.valueOf(Math.abs(num)).length();
    }

    public static String urlDecode(Object obj)
    {
        if (HanHelper.isEmpty(obj))
            return null;
        try
        {
            return URLDecoder.decode(obj.toString(), "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            LOG.error("urlDecode throw exception '{}'", e.getMessage());
        }
        return null;
    }

    /**
     * 把一个非空值加入到列表中
     * 
     * @param list
     * @param obj
     */
    public static <T> void addCollection(Collection<T> list, T obj)
    {
        if (obj != null)
            list.add(obj);
    }



    /**
     * 把数组中所有的元素放入结合中
     * 
     * @param list
     * @param arrays
     */
    public static <T> void putAll(Collection<T> list, T[] arrays)
    {
        if (HanHelper.isEmpty(arrays))
            return;

        for (T t : arrays)
            list.add(t);
    }

    public static int[] intListToArray(List<Integer> v)
    {
        if (HanHelper.isEmpty(v))
            return null;
        int[] iv = new int[v.size()];
        for (int i = 0; i < v.size(); ++i)
        {
            iv[i] = v.get(i).intValue();
        }
        return iv;
    }

    public static List<Integer> intArrayToList(int[] iv)
    {
        if (iv == null || iv.length == 0)
            return null;
        List<Integer> v = new ArrayList<Integer>();
        for (int i = 0; i < iv.length; ++i)
        {
            v.add(iv[i]);
        }

        return v;
    }

    public static long[] longListToArray(List<Long> v)
    {
        if (HanHelper.isEmpty(v))
            return null;
        long[] iv = new long[v.size()];
        for (int i = 0; i < v.size(); ++i)
        {
            iv[i] = v.get(i).longValue();
        }
        return iv;
    }

    public static List<Long> longArrayToList(long[] iv)
    {
        if (iv == null || iv.length == 0)
            return null;
        List<Long> v = new ArrayList<Long>();
        for (int i = 0; i < iv.length; ++i)
        {
            v.add(iv[i]);
        }

        return v;
    }

    public static boolean[] booleanListToArray(List<Boolean> v)
    {
        if (HanHelper.isEmpty(v))
            return null;
        boolean[] iv = new boolean[v.size()];
        for (int i = 0; i < v.size(); ++i)
        {
            iv[i] = v.get(i).booleanValue();
        }
        return iv;
    }

    public static List<Boolean> booleanArrayToList(boolean[] iv)
    {
        if (iv == null || iv.length == 0)
            return null;
        List<Boolean> v = new ArrayList<Boolean>();
        for (int i = 0; i < iv.length; ++i)
        {
            v.add(iv[i]);
        }

        return v;
    }


    public static String ensureLength(String s, int length, char defaultChar)
    {
        StringBuffer sb = null;
        if (s == null)
            s = "";
        if (s.length() < length)
        {
            sb = new StringBuffer(s);
            while (sb.length() < length)
                sb.append(defaultChar);
            return sb.toString();
        }
        if (s.length() > length)
            return s.substring(0, length);
        return s;
    }

    /**
     * @description: 字节数组合并方法
     */
    public static byte[] byteMerger(byte[] byte_1, byte[] byte_2)
    {
        byte[] byte_3 = new byte[byte_1.length + byte_2.length];
        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
        return byte_3;
    }

    /**
     * @description: 截取byte数组
     */
    public static byte[] subBytes(byte[] src, int begin, int count)
    {
        byte[] bs = new byte[count];
        System.arraycopy(src, begin, bs, 0, count);
        return bs;
    }

}
