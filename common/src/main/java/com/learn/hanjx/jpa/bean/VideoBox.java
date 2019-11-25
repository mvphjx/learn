package com.learn.hanjx.jpa.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="videoboxplus")
public class VideoBox {
	private int id;
	private String title;
	//http://www.91p02.space/view_video.php?viewkey=b9592288d809665cfeb2&page=3497&viewtype=basic&category=mr
	private String url;
	private int page;//倒序排列
	private String viewkey;
	private String autor;
	private String autorurl;
	private int viewnum;
	private int messagesnum;
	private int collectnum;
	private Date createtime=new Date();
	@Id @GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	@Column(name = "viewkey", unique = true)
	public String getViewkey() {
		return viewkey;
	}
	public void setViewkey(String viewkey) {
		this.viewkey = viewkey;
	}
	public String getAutorurl() {
		return autorurl;
	}
	public void setAutorurl(String autorurl) {
		this.autorurl = autorurl;
	}
	public int getViewnum() {
		return viewnum;
	}
	public void setViewnum(int viewnum) {
		this.viewnum = viewnum;
	}
	public int getMessagesnum() {
		return messagesnum;
	}
	public void setMessagesnum(int messagesnum) {
		this.messagesnum = messagesnum;
	}
	public int getCollectnum() {
		return collectnum;
	}
	public void setCollectnum(int collectnum) {
		this.collectnum = collectnum;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
}
