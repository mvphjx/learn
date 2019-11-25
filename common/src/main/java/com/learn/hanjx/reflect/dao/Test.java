package com.learn.hanjx.reflect.dao;
/*
 * 通过反射
 * 自己封装的ORM框架
 */

public class Test {

    public static void main(String args[]) {

        //获得NetJavaSession对象
        NetJavaSession session = new NetJavaSession();
        //创建一个UserInfo对象
        UserInfo user = new UserInfo();
        //设置对象的属性
        user.setId(5210);
        user.setAge(44);
        user.setPwd("pwd");
        user.setName("champion");
        //将对象保存到数据库中
        session.saveObject(user);
        //查找对象
        UserInfo userInfo = (UserInfo) session.getObject("com.learn.hanjx.reflect.dao.UserInfo", 5210);
        System.out.println("获取到的信息：" + userInfo);
        

    }
    public static void fun() {
    	//获得NetJavaSession对象
        NetJavaSession session = new NetJavaSession();
        //创建一个StudentInfo对象
    	StudentInfo stu = new StudentInfo();
        //设置对象的属性
    	stu.setId(5210);
    	stu.setAge(44);
    	stu.setPwd("pwd");
    	stu.setName("champion");
        //将对象保存到数据库中
        session.saveObject(stu);
        //查找对象
        UserInfo userInfo = (UserInfo) session.getObject("com.learn.hanjx.reflect.dao.StudentInfo", 5210);
        System.out.println("获取到的信息：" + userInfo);
    	
    }


}