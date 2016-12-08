package com.learn.hanjx.email;
import javax.mail.Message.RecipientType;  
/**邮件信息*/  
public class MessageInfo {  
    /**发送人，要和登入的邮箱的用户名一致*/  
    private String sendFrom;  
    private String sendTo;   
    private RecipientType recipientType;  //发送cc to bcc  
    /**主题*/  
    private String subject;  
    /**发送的内容*/  
    private String sendContext;  
    public String getSendFrom() {  
        return sendFrom;   
    }  
    public RecipientType getRecipientType() {  
        return recipientType;  
    }  
    public void setRecipientType(RecipientType recipientType) {  
        this.recipientType = recipientType;  
    }  
    public void setSendFrom(String sendFrom) {  
        this.sendFrom = sendFrom;  
    }  
    public String getSendTo() {  
        return sendTo;  
    }  
    public void setSendTo(String sendTo) {  
        this.sendTo = sendTo;  
    }  
    public String getSubject() {  
        return subject;  
    }  
    public void setSubject(String subject) {  
        this.subject = subject;  
    }  
    public String getSendContext() {  
        return sendContext;  
    }  
    public void setSendContext(String sendContext) {  
        this.sendContext = sendContext;  
    }  
}