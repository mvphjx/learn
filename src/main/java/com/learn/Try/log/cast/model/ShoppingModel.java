package com.learn.Try.log.cast.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ShoppingModel {
	public static Map<Integer,String> map=new HashMap();
	{
		map.put(1, "生活一般花费");
		map.put(2, "房屋相关");
		map.put(3, "其他生活花费");
		map.put(4, "精神提升");
	}
	private String id;
	private Double money;
	private String name;
	private String type;
	private String remark;
	private Date creattime;
	public String getId() {
		return id;
	}
	@Override
	public String toString() {
		return "ShoppingModel [id=" + id + ", money=" + money + ", name="
				+ name + ", type=" + type + ", remark=" + remark
				+ ", creattime=" + creattime + "]";
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreattime() {
		return creattime;
	}
	public void setCreattime(Date creattime) {
		this.creattime = creattime;
	}
	public ShoppingModel(String id, Double money, String name, String type,
			String remark, Date creattime) {
		super();
		this.id = id;
		this.money = money;
		this.name = name;
		this.type = type;
		this.remark = remark;
		this.creattime = creattime;
	}
	
	

}
