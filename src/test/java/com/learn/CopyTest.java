package com.learn;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用bat 批处理copy
 * copy指定文件
 * Created by han on 2017/12/16.
 */
public class CopyTest
{
    //copy c:\123.txt d:\abc.bat
    public static void main(String[] args)
    {
        List<String> listName = new ArrayList<>();
        listName.add("1");
        listName.add("2");
        listName.add("3");
        listName.add("4");
        String dir1 = "C:\\Users\\han\\Desktop\\bak\\web\\html2\\";
        String dir2 = "C:\\Users\\han\\Desktop\\bak\\web\\html2\\demo";
        for(String name : listName){
            System.out.println("copy "+dir1 + name+".html  "+dir2);
        }

    }
}
