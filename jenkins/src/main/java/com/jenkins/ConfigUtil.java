package com.jenkins;

import cn.hutool.core.util.XmlUtil;
import com.offbytwo.jenkins.JenkinsServer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ConfigUtil
{




    /**
     * 创建/修改视图
     * 可以采用正则表带是匹配，显示jobs
     *      \S*web\S*20210514$
     *          -》habis_web_hitlog_20210514
     *
     */




    /**
     * 修改邮箱
     *
     * @param jenkins 服务器
     * @param jobName job名
     * @param email   邮箱地址 ;分隔
     * @throws Exception
     */
    public static void updateEmail(JenkinsServer jenkins, String jobName, String email, boolean append)
    {
        try
        {
            String xpath = "//reporters/hudson.maven.reporters.MavenMailer/recipients";
            String jobXml = jenkins.getJobXml(jobName);
            Document document = XmlUtil.readXML(jobXml);
            Element element = XmlUtil.getElementByXPath(xpath, document);
            if (append)
            {
                email = element.getTextContent() +","+ email;
            }
            element.setTextContent(email);
            String jobXml2 = XmlUtil.format(document);
            System.out.println(jobName);
            jenkins.updateJob(jobName, jobXml2);
        }
        catch (Exception e)
        {

        }

    }
}
