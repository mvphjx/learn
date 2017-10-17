package com.learn.Try.T2017.T09.T10;

import org.apache.commons.lang.ArrayUtils;
import org.hibernate.boot.jaxb.SourceType;

import java.io.UnsupportedEncodingException;

/**
 * 字节数组转换为 字符串
 * 主要是ASCII编码
 * Created by han on 2017/10/17.
 */
public class BytesTest
{
    public static void main(String[] args) throws UnsupportedEncodingException
    {
        byte[] arr = {99,50,62,0,-12};
        String val = new String(arr, 0,ArrayUtils.indexOf(arr,(byte)0)+1,"UTF-8");
        System.out.println(val);
    }
}
