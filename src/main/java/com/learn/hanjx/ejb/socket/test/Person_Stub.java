package com.learn.hanjx.ejb.socket.test;
/*
 * http://yunshuisuiyuan.iteye.com/blog/1845588
 */
// 客户端操作 stub类  负责告诉 skeleton ：我要调用哪个方法  
import java.io.ObjectOutputStream;  
import java.io.ObjectInputStream;  
import java.net.Socket;  

public class Person_Stub implements Person {  
    Socket socket;  

    public Person_Stub() throws Throwable {  
        // connect to skeleton  
        socket = new Socket("localhost", 9000);  
    }  

    public int getAge() throws Throwable {  
        // pass method name to skeleton  
        ObjectOutputStream outStream =  
            new ObjectOutputStream(socket.getOutputStream());;  
        outStream.writeObject("age");;  
        outStream.flush();;  
        ObjectInputStream inStream =  
            new ObjectInputStream(socket.getInputStream());;  
        return inStream.readInt();
    }  

    public String getName() throws Throwable {  
        // pass method name to skeleton  
        ObjectOutputStream outStream =  
            new ObjectOutputStream(socket.getOutputStream());;  
        outStream.writeObject("name");  
        outStream.flush();  

        ObjectInputStream inStream =  
            new ObjectInputStream(socket.getInputStream());;  
        return (String)inStream.readObject();  
    }
    public static void main(String[] args) throws Throwable {
    	Person_Stub ps = new Person_Stub();
    	System.out.println(ps.getName());
    	System.out.println(ps.getAge());
    	ps.socket.close();
	}
}  
