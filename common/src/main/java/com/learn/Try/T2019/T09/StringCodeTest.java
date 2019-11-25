package com.learn.Try.T2019.T09;

import com.learn.hanjx.design.proxy.Source;
import com.learn.hanjx.util.io.FileUtil;
import org.apache.poi.openxml4j.opc.internal.FileHelper;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class StringCodeTest
{
    public static void main(String[] args) throws UnsupportedEncodingException
    {

        String str = "CN=姓名阿 身份证号, OU=00, OU=10, O=40, L=00, L=00, ST=11, C=CN";
        byte[] b_gbk = str.getBytes("GBK");   //b_gbk的长度为2
        byte[] b_utf8 = str.getBytes("UTF-8");   //b_utf8的长度为3
        byte[] b_iso88591 = str.getBytes("ISO8859-1");//   b_iso88591的长度为1
        byte[] b_unicode = str.getBytes("unicode");  //b_unicode长度为4
        String s_gbk = new String(b_gbk,"GBK");
        String s_utf8 = new String(b_utf8,"UTF-8");
        String s_iso88591 = new String(b_iso88591,"ISO8859-1");
        String s_unicode = new String(b_unicode, "unicode");

        System.out.println(s_gbk);
        System.out.println(Encoding.getEncoding(s_gbk));
        System.out.println(s_utf8);
        System.out.println(Encoding.getEncoding(s_utf8));
        System.out.println(s_iso88591);
        System.out.println(Encoding.getEncoding(s_iso88591));
        System.out.println(s_unicode);
        System.out.println(Encoding.getEncoding(s_unicode));

    }
    @Test
    public void test() throws UnsupportedEncodingException
    {
        byte[] b_iso88591 = "深".getBytes("GBK");//   b_iso88591的长度为1
        String s_gbk = new String(b_iso88591,"GBK");
        String s_utf8 = new String(b_iso88591,"UTF-8");
        String s_iso88591 = new String(b_iso88591,"ISO8859-1");
        String s_unicode = new String(b_iso88591, "unicode");

        System.out.println(s_gbk);
        System.out.println(Encoding.getEncoding(s_gbk));
        System.out.println(s_utf8);
        System.out.println(Encoding.getEncoding(s_utf8));
        System.out.println(s_iso88591);
        System.out.println(Encoding.getEncoding(s_iso88591));
        System.out.println(s_unicode);
        System.out.println(Encoding.getEncoding(s_unicode));

    }

    @Test
    public void test2() throws UnsupportedEncodingException
    {
        String fileName = "C:\\GITHUB\\learn\\src\\main\\resources\\gbk.txt";
        byte[] bytes = FileUtil.loadDataFromFile(fileName);
        String gbk = new String(bytes,"GBK");
        System.out.println(gbk);
        System.out.println(Encoding.getEncoding(gbk));

        String utf = new String(bytes,"UTF-8");
        System.out.println(utf);
    }
}
