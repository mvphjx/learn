package com.learn.Try.T2019.T09;

import org.jboss.netty.util.CharsetUtil;

import java.io.UnsupportedEncodingException;

public class StringCodeDemo
{
    public static void main(String[] args) throws UnsupportedEncodingException
    {
        String testData = "123abc数据";
        //此次测试前提：参数包含中文
        testU8(testData);//UTF_8-String：123abc数据
        testIso(testData);//ISO_8859_1-String：123abc??
        testChineseTrue(testData);//test3-String：123abc数据
        testChineseFalse(testData);//test4-String：123abc??
    }

    /**
     * 正常返回：UTF_8-String：123abc数据
     *
     * @param testdata
     */
    public static void testU8(String testdata)
    {
        byte[] bytes = testdata.getBytes(CharsetUtil.UTF_8);
        String result = new String(bytes, CharsetUtil.UTF_8);
        System.out.println("UTF_8-String：" + result);
    }

    /**
     * 返回有乱码：123abc??
     * 原因：ISO8859-1编码的编码表中，根本就没有包含汉字字符。
     *
     * @param testdata
     */
    public static void testIso(String testdata)
    {
        byte[] bytes = testdata.getBytes(CharsetUtil.ISO_8859_1);
        String result = new String(bytes, CharsetUtil.ISO_8859_1);
        System.out.println("ISO_8859_1-String：" + result);
    }

    /**
     * 返回：123abc数据
     * 通过String.getBytes(String decode)方法来得到byte[]时，一定要确定decode的编码表中确实存在String表示的码值。
     * gbk/utf-8都可以
     *
     * @param testdata
     */
    public static void testChineseTrue(String testdata) throws UnsupportedEncodingException
    {
        Object message = null;
        message = new String(testdata.getBytes("GBK"), CharsetUtil.ISO_8859_1);
        String returnData = message.toString();
        //解析
        byte[] xmlByte = returnData.getBytes(CharsetUtil.ISO_8859_1);
        String xml = "";
        xml = new String(xmlByte, "GBK");
        System.out.println("test3-String：" + xml);
    }

    /**
     * 当传入的参数包含中文时，执行该方法出现乱码。 返回：test4-String：123abc??
     * 原因：通过String.getBytes(String decode)方法来得到byte[]时，一定要确定decode的编码表中确实存在String表示的码值。
     * 而ISO8859-1编码的编码表中，根本就没有包含汉字字符。
     *
     * @param testdata
     */
    public static void testChineseFalse(String testdata) throws UnsupportedEncodingException
    {
        Object message = null;
        message = new String(testdata.getBytes(CharsetUtil.ISO_8859_1), "GBK");
        String returnData = message.toString();
        //解析
        byte[] xmlByte = returnData.getBytes("GBK");
        String xml = "";
        xml = new String(xmlByte, CharsetUtil.ISO_8859_1);
        System.out.println("test4-String：" + xml);
    }
}
