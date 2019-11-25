package com.learn.hanjx.jpa.test;
/**
 * 测试 persistence.xml 读取路径
 */

import java.net.URL;

public class PersistenceXmlLoadTest
{
    public static void main(String[] args)
    {
        final ClassLoader classLoader = PersistenceXmlLoadTest.class.getClassLoader();
        System.out.println(classLoader.getResource("log4j.properties"));
        //System.out.println(classLoader.getResource("META-INF/persistence.xml"));
        String path = "META-INF/persistence.xml";
        final URL resource = classLoader.getResource(path);
        System.out.println(resource);

    }
}
