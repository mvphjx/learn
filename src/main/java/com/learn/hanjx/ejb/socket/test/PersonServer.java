package com.learn.hanjx.ejb.socket.test;

//接口实现类 方法返回具体的消息  
public class PersonServer implements Person  
{  
    int age;  
    String name;  

    public PersonServer(String name, int age) {  
        this.age = age;  
        this.name = name;  
    }  

    public int getAge(){  
        return age;  
    }  

    public String getName(){  
        return name;  
    }  
}