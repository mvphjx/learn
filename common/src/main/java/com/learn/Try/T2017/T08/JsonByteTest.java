package com.learn.Try.T2017.T08;

import com.learn.hanjx.util.MsgBase64;
import com.learn.hanjx.util.json.JsonUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * Created by han on 2017/8/14.
 *
 * 测试了一下 json 序列化 自动将字节数组base64编码
 */
public class JsonByteTest
{
    public static void main(String[] args)
    {
        CellInfo cellInfo = new CellInfo();
        String json = JsonUtil.createJsDataByJackson(cellInfo);
        System.out.println(json);
    }
}
class CellInfo{
    byte[] value = {1, 2, 3, 4, 5, 7, 8};
    public int	cprLength = 2;
    {
        int length = 300;
        value = new byte[3000];
        new Random().nextBytes(value);
    }
}
