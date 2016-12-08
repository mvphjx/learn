package com.learn.hanjx.email;

import java.util.Date;  

import javax.mail.Message;  
import javax.mail.MessagingException;  
import javax.mail.Session;  
import javax.mail.Transport;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeMessage;  
  
public class MessageSender {  
      
    private Session session;  
      
    public MessageSender(MailUserInfo mailUserInfo){  
        //创建连接邮箱  
        session = Session.getDefaultInstance(mailUserInfo.getProp(),mailUserInfo);  
    }  
      
    public void sendMessage(MessageInfo messageInfo) throws MessagingException{  
        Transport.send(createMessage(messageInfo));  
    }  
      
    /**对邮件信息进行封装*/  
    protected Message createMessage(MessageInfo messageInfo) throws MessagingException{  
        Message message = new MimeMessage(session);  
        message.setFrom(new InternetAddress(messageInfo.getSendFrom())); //  
        message.setRecipient(messageInfo.getRecipientType(),new InternetAddress(messageInfo.getSendTo()));  
        message.setSubject(messageInfo.getSubject());  
        message.setSentDate(new Date());  
        message.setText(messageInfo.getSendContext());  
        return message;  
    }  
}  