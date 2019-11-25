package com.learn.hanjx.reflect.dao;
/**
 * 用户实体类Model
 * 
 * 
-- Create table
create table USERINFO
(
  pwd  NVARCHAR2(100),
  age  NUMBER,
  name NVARCHAR2(100),
  id   NUMBER
)

mysql Create table
create table USERINFO
(
  pwd  VARCHAR(100),
  age  numeric,
  name VARCHAR(100),
  id   numeric
);
 * 
 */

public class UserInfo {

private int id;

private String name;

private String pwd;

private int age;

 

@Override

public String toString() {

    return "UserInfo [id=" + id + ", name=" + name + ", pwd=" + pwd + ", age="

            + age + "]";

}

public int getId() {

    return id;

}

public void setId(int id) {

    this.id = id;

}

public String getName() {

    return name;

}

public void setName(String name) {

    this.name = name;

}

public String getPwd() {

    return pwd;

}

public void setPwd(String pwd) {

    this.pwd = pwd;

}

public int getAge() {

    return age;

}

public void setAge(int age) {

    this.age = age;

}

 

}