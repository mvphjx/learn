package com.webMagic.model;

import com.webMagic.base.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 爬取视频信息  对象
 -- auto-generated definition

 create table VIDEO_INFO
 (
 ID INTEGER not null
 primary key,
 AUTOR VARCHAR(255),
 AUTORURL VARCHAR(255),
 COLLECTNUM INTEGER not null,
 CREATETIME DATE,
 MESSAGESNUM INTEGER not null,
 PAGENUM INTEGER not null,
 TITLE VARCHAR(255),
 URL VARCHAR(255),
 VIEWKEY VARCHAR(255),
 VIEWNUM INTEGER not null
 );
 /


 */
@Entity
@Table(name="VIDEO_INFO")
public class VideoModel extends BaseEntity{
	private static final long serialVersionUID = 3783338463831001070L;

	private String title;
	//http://www.91p02.space/view_video.php?viewkey=b9592288d809665cfeb2&page=3497&viewtype=basic&category=mr
	private String url;
	private int pagenum;//倒序排列
	private String viewkey;
	private String autor;
	private String autorurl;
	private int viewnum;
	private int messagesnum;
	private int collectnum;
	private Date createtime=new Date();
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

	public int getPagenum() {
		return pagenum;
	}

	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}
	@Override
	public String toString() {
		return "VideoModel{" +
				"id=" + getId() +
				", title='" + title + '\'' +
				", url='" + url + '\'' +
				", page=" + pagenum +
				", viewkey='" + viewkey + '\'' +
				", autor='" + autor + '\'' +
				", autorurl='" + autorurl + '\'' +
				", viewnum=" + viewnum +
				", messagesnum=" + messagesnum +
				", collectnum=" + collectnum +
				", createtime=" + createtime +
				'}';
	}
}
