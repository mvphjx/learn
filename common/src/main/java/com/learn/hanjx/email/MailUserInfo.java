package com.learn.hanjx.email;

import java.util.Properties;  

import javax.mail.Authenticator;  
import javax.mail.PasswordAuthentication;  
/** 
 * 发件人信息,继承Authenticator重写getPasswordAuthentication, 
 * 可以进行验证邮箱的密码和用户名 
 */  
public class MailUserInfo extends Authenticator{  
      
    private String user;  //用户名  
    private String password; //密码  
    private String mailServerHost;  
    private int mailServerPort;  
      
    public MailUserInfo(String user, String password, String mailServerHost,  
            int mailServerPort) {  
        this.user = user;  
        this.password = password;  
        this.mailServerHost = mailServerHost;  
        this.mailServerPort = mailServerPort;  
    }  
    /**邮件的默认配置，注意*/  
    public Properties getProp(){  
        Properties prop = new Properties();  
        prop.put("mail.smtp.host", this.mailServerHost);      
        prop.put("mail.smtp.port", this.mailServerPort);   
        prop.put("mail.transport.protocol","stmp");  
        prop.put("mail.store.protocol","imap");  
        prop.put("mail.imap.class","com.sun.mail.imap.IMAPStore");  
        prop.put("mail.smtp.class","com.sun.mail.smtp.SMTPTransport");  
        //com.sun.mail.smtp.SMTPSendFailedException: 503 Error: need EHLO and AUTH first !  
        //如果不配置下面这段话  
        prop.put("mail.smtp.auth", "true");  
        prop.put("mail.debug", "true");
        prop.put("mail.smtp.starttls.enable","true");  //imposta la connessione cifrata
        return prop;  
    }  
  
    @Override  
    protected PasswordAuthentication getPasswordAuthentication() {  
        return new PasswordAuthentication(user,password);  
    }  
}  