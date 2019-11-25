package com.learn.Try.google.protobuf.test;

import java.io.Serializable;
import java.util.List;

public class Teacher implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private List<Student> stuList;
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
	public List<Student> getStuList() {
		return stuList;
	}
	public void setStuList(List<Student> stuList) {
		this.stuList = stuList;
	}
	
}
