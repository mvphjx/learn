package com.learn.redis.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class RedisDemo {
	private int id;
	private String name;
	private Date birthDay;
	private String info;
	private Byte[] filebyte;//延迟加载这个属性
	private List<String> list ;
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
	
	
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@Override
	public String toString() {
		return "RedisDemo [id=" + id + ", name=" + name + ", birthDay="
				+ birthDay + ", info=" + info + ", filebyte="
				+ Arrays.toString(filebyte) + ", list=" + list + "]";
	}
	public Byte[] getFilebyte() {
		return filebyte;
	}
	public void setFilebyte(Byte[] filebyte) {
		this.filebyte = filebyte;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	
	
}
