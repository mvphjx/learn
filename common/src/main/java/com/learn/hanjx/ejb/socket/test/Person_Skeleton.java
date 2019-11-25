package com.learn.hanjx.ejb.socket.test;

// 服务端skeleton类  负责通过实现类 调用stub类指定的方法  
import java.io.IOException;
import java.io.ObjectOutputStream;  
import java.io.ObjectInputStream;  
import java.net.Socket;  
import java.net.ServerSocket;  
import java.util.ArrayList;
import java.util.List;

public class Person_Skeleton extends Thread {  
	private List<Exception> exception = new ArrayList<Exception>();
	PersonServer myServer;  
    

    public Person_Skeleton(PersonServer server){  
        // get reference of object server  
        this.myServer = server;  
    }  

    public void run()  { 
    	ServerSocket serverSocket=null;
    	try{
            // new socket at port 9000  
            serverSocket = new ServerSocket(9000);  
            // accept stub's request  
            Socket socket = serverSocket.accept();  
            while (socket != null) {  
                // get stub's request  
                ObjectInputStream inStream =  
                    new ObjectInputStream(socket.getInputStream());  
                String method = (String)inStream.readObject(); 
                ObjectOutputStream outStream =  
                        new ObjectOutputStream(socket.getOutputStream()); 
                // check method name  
                if (method.equals("age")){  
                    // execute object server's business method  
                    int age = myServer.getAge();  
                    // return result to stub  
                    outStream.writeInt(age);  
                    outStream.flush();  
                }  

                if(method.equals("name")) {  
                    // execute object server's business method  
                    String name = myServer.getName();  
                    // return result to stub  
                    outStream.writeObject(name);  
                    outStream.flush();  
                }  
            }  
    	}catch(IOException e){
    		exception.add(e);
    	}catch(ClassNotFoundException e){
    		exception.add(e);
    	}finally{
    		try {
    			if(serverSocket!=null)
    				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
				exception.add(e);
			}
    	}
         
    }  

    public static void main(String args []) {  
        // new object server  
        PersonServer person = new PersonServer("Richard", 34);  
        Person_Skeleton skel = new Person_Skeleton(person);  
        skel.start();  
    }  
}