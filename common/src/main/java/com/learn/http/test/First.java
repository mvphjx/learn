package com.learn.http.test;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public class First
{
    public static void main(String[] args) throws Exception
    {
        List<NameValuePair> formparams = new ArrayList<>();
        formparams.add(new BasicNameValuePair("account", ""));
        formparams.add(new BasicNameValuePair("password", ""));
        HttpEntity reqEntity = new UrlEncodedFormEntity(formparams, "utf-8");

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)//一、连接超时：connectionTimeout-->指的是连接一个url的连接等待时间
                .setSocketTimeout(5000)// 二、读取数据超时：SocketTimeout-->指的是连接上一个url，获取response的返回等待时间
                .setConnectionRequestTimeout(5000)
                .build();

        HttpClient client = new DefaultHttpClient();
        //https://mp.weixin.qq.com
        HttpGet get = new HttpGet("https://github.com");
        HttpResponse response = client.execute(get);
        if (response.getStatusLine().getStatusCode() == 200)
        {
            HttpEntity resEntity = response.getEntity();
            String message = EntityUtils.toString(resEntity, "utf-8");
            System.out.println(message);
        } else
        {
            System.out.println("请求失败");
        }
    }

}