package com.learn.hanjx.jpa.bean;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown=true)
@Entity(name="Person")
public class Person {
	
	private int id;
	private String name;
	@JsonProperty("BIRTH_DAY")
	private Date birthDay;
	private Gender gender = Gender.MAN;
	private String info;
	private Byte[] filebyte;//延迟加载这个属性
	private String imagepath;//数据库中不创建这个字段
	private IDCard idcard;
	
	public Person(){};
	public Person(String name){
		this.name = name;
	}
	@Id @GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(length=100,nullable=false,name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	@Enumerated(EnumType.STRING) @Column(length=5,nullable=false)
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	@Lob
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@Lob @Basic(fetch=FetchType.LAZY)
	public Byte[] getFilebyte() {
		return filebyte;
	}
	public void setFilebyte(Byte[] filebyte) {
		this.filebyte = filebyte;
	}
	@Transient
	public String getImagepath() {
		return imagepath;
	}
	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	@OneToOne(optional=false,cascade=CascadeType.ALL)
	@JoinColumn(name="idcard_id")
	public IDCard getIdcard() {
		return idcard;
	}
	public void setIdcard(IDCard idcard) {
		this.idcard = idcard;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", birthDay=" + birthDay
				+ ", gender=" + gender + ", info=" + info + ", filebyte="
				+ Arrays.toString(filebyte) + ", imagepath=" + imagepath
				+ ", idcard=" + idcard + "]";
	}
	
}
