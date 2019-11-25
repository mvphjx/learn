package com.learn;

import com.learn.hanjx.util.io.FileReadUtils;
import com.learn.hanjx.util.io.FileUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * 使用bat 批处理copy
 * copy指定文件
 * Created by han on 2017/12/16.
 */
public class Base64Test
{
    //copy c:\123.txt d:\abc.bat
    public static void main(String[] args) throws UnsupportedEncodingException
    {

        byte[] asBytes = Base64.getDecoder().decode("MzcwMzA0MTk4OTEwMjE2MjE3");
        System.out.println(new String(asBytes));
    }
    @Test
    public void loadFile() throws IOException
    {
        String fileName = "D:\\temp\\lp.txt";
        BufferedReader bufferedReader = FileReadUtils.readTxtFileReader(fileName);
        String baseStr = bufferedReader.readLine();
        byte[] asBytes = Base64.getDecoder().decode(baseStr);
        FileUtils.wirterFile(fileName+".new",asBytes);
    }
}
