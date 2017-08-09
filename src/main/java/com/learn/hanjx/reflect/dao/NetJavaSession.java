package com.learn.hanjx.reflect.dao;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NetJavaSession {

    /**

     * 解析出保存对象的sql语句

     *

     * @param object

     *            ：需要保存的对象

     * @return：保存对象的sql语句

     */

    public static String getSaveObjectSql(Object object) {

        // 定义一个sql字符串

        String sql = "insert into ";

        // 得到对象的类

        Class c = object.getClass();

        // 得到对象中所有的方法

        Method[] methods = c.getMethods();

        // 得到对象中所有的属性

        Field[] fields = c.getFields();

        // 得到对象类的名字

        String cName = c.getName();

        // 从类的名字中解析出表名

        String tableName = cName.substring(cName.lastIndexOf(".") + 1,

                cName.length());

        sql += tableName + "(";

        List<String> mList = new ArrayList<String>();

        List vList = new ArrayList();
//根据get方法获取  model属性 组成sql
        for (Method method : methods) {

            String mName = method.getName();

            if (mName.startsWith("get") && !mName.startsWith("getClass")) {

                String fieldName = mName.substring(3, mName.length());

                mList.add(fieldName);

                System.out.println("字段名字----->" + fieldName);

                try {

                    Object value = method.invoke(object, null);

                    System.out.println("执行方法返回的值：" + value);

                    if (value instanceof String) {

                        vList.add("'" + value + "'");

                        System.out.println("字段值------>" + value);

                    } else {

                        vList.add(value);

                    }

                } catch (Exception e) {

                    e.printStackTrace();

                }

            }

        }

        for (int i = 0; i < mList.size(); i++) {

            if (i < mList.size() - 1) {

                sql += mList.get(i) + ",";

            } else {

                sql += mList.get(i) + ") values(";

            }

        }

        for (int i = 0; i < vList.size(); i++) {

            if (i < vList.size() - 1) {

                sql += vList.get(i) + ",";

            } else {

                sql += vList.get(i) + ")";

            }

        }

 

        return sql;

    }

 

    public static List getDatasFromDB(String tableName, int Id) {

 

        return null;

 

    }

 

    /**

     * 将对象保存到数据库中

     *

     * @param object

     *            ：需要保存的对象

     * @return：方法执行的结果;1:表示成功，0:表示失败

     */

    public int saveObject(Object object) {

        Connection con =Connect2DBFactory.getDBConnection();

        String sql = getSaveObjectSql(object);
        System.out.println("SQL:"+sql);
        try {

            // Statement statement=(Statement) con.createStatement();

            PreparedStatement psmt = con.prepareStatement(sql);

            psmt.executeUpdate();

            return 1;

        } catch (SQLException e) {

            e.printStackTrace();

            return 0;

        }

    }

 

    /**

     * 从数据库中取得对象

     *

     * @param arg0

     *            ：对象所属的类

     * @param id

     *            ：对象的id

     * @return:需要查找的对象

     */

    public Object getObject(String className, int Id) {

        // 得到表名字

        String tableName = className.substring(className.lastIndexOf(".") + 1,

                className.length());

        // 根据类名来创建Class对象

        Class c = null;

        try {

            c = Class.forName(className);

 

        } catch (ClassNotFoundException e1) {

 

            e1.printStackTrace();

        }

        // 拼凑查询sql语句

        String sql = "select * from " + tableName + " where Id=" + Id;

        System.out.println("查找sql语句：" + sql);

        // 获得数据库链接

        Connection con =  Connect2DBFactory.getDBConnection();

        // 创建类的实例

        Object obj = null;

        try {

            Statement stm = con.createStatement();

            // 得到执行查寻语句返回的结果集

            ResultSet set = stm.executeQuery(sql);

            // 得到对象的方法数组

            Method[] methods = c.getMethods();

            // 遍历结果集

            if (set.next()) {

                obj = c.newInstance();

                // 遍历对象的方法

                for (Method method : methods) {

                    String methodName = method.getName();

                    // 如果对象的方法以set开头

                    if (methodName.startsWith("set")) {

                        // 根据方法名字得到数据表格中字段的名字

                        String columnName = methodName.substring(3,

                                methodName.length());

                        // 得到方法的参数类型

                        Class[] parmts = method.getParameterTypes();

                        if (parmts[0] == String.class) {

                            // 如果参数为String类型，则从结果集中按照列名取得对应的值，并且执行改set方法

                            method.invoke(obj, set.getString(columnName));

                        }

                        if (parmts[0] == int.class) {

                            method.invoke(obj, set.getInt(columnName));

                        }

                    }

 

                }

            }

 

        } catch (Exception e) {

            e.printStackTrace();

        }

        return obj;

    }

} 
