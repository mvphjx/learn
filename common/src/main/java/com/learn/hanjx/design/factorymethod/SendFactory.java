package com.learn.hanjx.design.factorymethod;

public class SendFactory
{

    public static Sender produceMail()
    {
        return new MailSender();
    }

    public static Sender produceSms()
    {
        return new SmsSender();
    }

    public static Sender produce(String type)
    {
        if ("mail".equals(type))
        {
            return new MailSender();
        } else if ("sms".equals(type))
        {
            return new SmsSender();
        } else
        {
            System.out.println("请输入正确的类型!");
            return null;
        }
    }
}
