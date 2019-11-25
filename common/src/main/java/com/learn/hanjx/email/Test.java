package com.learn.hanjx.email;
import javax.mail.MessagingException;  
import javax.mail.Message.RecipientType;  
public class Test {
	public static void main(String[] args) throws MessagingException {  
        MailUserInfo mailUserInfo = new MailUserInfo("jinling@cbda.cn","///Mima1234","smtp.exmail.qq.com",25);
		//MailUserInfo mailUserInfo = new MailUserInfo("hjx511572653@qq.com","oanznasluukvcbbe","smtp.qq.com",25);
		MessageSender messageSender= new MessageSender(mailUserInfo);  
        MessageInfo messageInfo = new MessageInfo();  
        messageInfo.setRecipientType(RecipientType.TO);  
        messageInfo.setSubject("你好美女");  
        messageInfo.setSendContext("你是最厉害的，祝你天天开心");  
        messageInfo.setSendFrom("jinling@cbda.cn");  
        messageInfo.setSendTo("511572653@qq.com");  
        messageSender.sendMessage(messageInfo);  
    }  

}
