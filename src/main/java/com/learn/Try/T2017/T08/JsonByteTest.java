package com.learn.Try.T2017.T08;

import com.learn.hanjx.util.MsgBase64;
import com.learn.hanjx.util.json.JsonUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by han on 2017/8/14.
 *
 * 测试了一下 json 序列化 自动将字节数组base64编码
 */
public class JsonByteTest
{
    public static void main(String[] args)
    {
        byte[] bs = {1, 2, 3, 4, 5, 7, 8};
        String json = JsonUtil.createJsDataByJackson(bs);
        System.out.println(json);
        System.out.println(Arrays.toString(MsgBase64.fromBase64(json)));
    }
}
