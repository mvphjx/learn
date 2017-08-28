package com.learn.hanjx.util;

import com.learn.hanjx.exceptionAndError.exception.HanException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * BASE64编码
 * @author 北京北大高科指纹技术有限公司
 *         www.idfounder.com
 *         北京海鑫科金高科技股份有限公司
 *         www.hisign.com.cn
 * 创建日期：2016年10月10日下午3:20:45
 */
@SuppressWarnings("restriction")
public class MsgBase64
{
    static private final Logger LOG = LoggerFactory.getLogger(MsgBase64.class);
    /**
     *  得到字节数组的BASE64 字符串
     * @param data
     * @return
     */
    @SuppressWarnings("restriction")
    public static String toBase64(byte[] data)
    {
        if (data == null)
            return null;
        if (data.length == 0)
            return "";
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    public static boolean isValidBase64Char(char c)
    {
        if (c >= 'A' && c <= 'Z')
            return true;
        if (c >= 'a' && c <= 'z')
            return true;
        if (c >= '0' && c <= '9')
            return true;
        if (c == '/' || c == '+' || c == '=')
            return true;

        return false;
    }

    /**
     * 把BASE64字符串转换为字节数组。会抛出异常。
     * @param s
     * @return
     */
    @SuppressWarnings("restriction")
    public static byte[] fromBase64(String s) throws HanException
    {
        if (s == null || s.length() == 0)
            return null;
        StringBuilder sb = new StringBuilder();

        sb.ensureCapacity(s.length());
        for (int i = 0; i < s.length(); ++i)
        {
            if (isValidBase64Char(s.charAt(i)))
            {
                sb.append(s.charAt(i));
            }
        }
        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
        try
        {
            return decoder.decodeBuffer(sb.toString());
        }
        catch (IOException e)
        {
            LOG.error("fromBase64 throw exception '{}'", e.getMessage());
        }
        return null;
    }

}
