package com.webMagic.model;

/**
 * 统一ajax 返回json数据格式
 * Created by han on 2017/12/3.
 */
public class MessageObject {
    private Object data;
    private String result;

    public MessageObject(Object data, String result) {
        this.data = data;
        this.result = result;
    }
    public static MessageObject  createSuccessMessage(Object data){
        return new MessageObject(data, Result.SUCCESS);
    }
    public static MessageObject  createErrorMessage(Object data){
        return new MessageObject(data, Result.ERROR);
    }
    public static MessageObject  createErrorMessage(){
        return new MessageObject(null, Result.ERROR);
    }
    public Object getData() {
        return data;
    }
    public String getResult() {
        return result;
    }
}
interface Result {
    String SUCCESS = "success";
    String ERROR = "error";
}