package com.learn.hanjx.reflect.dao;
/*
 * 提供数据库连接的工厂类
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class Connect2DBFactory {

    public static Connection getDBConnection() {

        Connection conn = null;

        try {

//            Class.forName("oracle.jdbc.driver.OracleDriver");
//
//            String url = "jdbc:oracle:thin:@localhost:1521:orcl";// 
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/work?useUnicode=true&amp;characterEncoding=utf-8";// 
            
            

            String user = "root";

            String password = "";

            conn = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {

            e.printStackTrace();

        }
        return conn;

    }

}