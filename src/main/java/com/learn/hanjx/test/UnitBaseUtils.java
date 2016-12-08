package com.learn.hanjx.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 测试工具类
 *
 */
public class UnitBaseUtils
{
    public static String JBossServerHost = "JBossServerHost";
    public static int JBossServerPort = 4443;
    public static String JBossServerUserName = "JBossServerUserName";
    public static String JBossServerPassword = "JBossServerPassword";
    public static String ABISUserName = "ysabisadm";
    public static String ABISPassword = "HelloUBC3";

    /**
     * 登录
     */
    public static void login()
    {
        init();
        //svrMgr.login(ABISUserName, ABISPassword);
        assert 1!=1;
    }

    /**
     * 初始化接口管理器
     */
    public static void init()
    {
        ServiceAuthInfo info = null;
        try
        {
            info = getInfoByXML();
        }
        catch (Exception e)
        {
            info = new ServiceAuthInfo();
        }
        //RmtOpiMgr.getInstance().init(info);

    }

    /**
     * 获取配置文件信息
     * @return
     * @throws FileNotFoundException
     * @throws DocumentException
     */
    public static ServiceAuthInfo getInfoByXML() throws FileNotFoundException, DocumentException
    {
        String xml = "src/main/webapp/WEB-INF/web.xml";
        ServiceAuthInfo info = new ServiceAuthInfo();
        // 读取指定的配置文件
        SAXReader reader = new SAXReader();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream ins = classLoader.getResourceAsStream(xml);
        if (ins == null)
        {
            ins = new FileInputStream(xml);
        }
        Document doc = reader.read(ins);
        Element root = doc.getRootElement();
        Element foo;
        Iterator<Element> i = root.elementIterator("context-param");
        while (i.hasNext())
        {
            foo = i.next();
            Element nameEl = foo.element("param-name");
            Element valueEl = foo.element("param-value");
            String name = nameEl.getStringValue();
            String value = valueEl.getStringValue();
            if (name == null || value == null)
            {
                continue;
            }
            if ("JBossServerHost".equalsIgnoreCase(name))
            {
                info.serverName = value;
                JBossServerHost = value;
            }
            else if ("JBossServerPort".equalsIgnoreCase(name))
            {
                info.serverPort = Integer.valueOf(value);
                JBossServerPort = Integer.valueOf(value);
            }
            else if ("JBossServerUserName".equalsIgnoreCase(name))
            {
                info.userName = value;
                JBossServerUserName = value;
            }
            else if ("JBossServerPassword".equalsIgnoreCase(name))
            {
                info.password = value;
                JBossServerPassword = value;
            }
            else if ("ABISUserName".equalsIgnoreCase(name))
            {
                ABISUserName = value;
            }
            else if ("ABISPassword".equalsIgnoreCase(name))
            {
                ABISPassword = value;
            }
        }
        return info;

    }

}
