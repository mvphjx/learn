package cn.com.beans;

import java.util.Date;

/**
 * 算法统计表的信息
 * @author Administrator
 *
 */
public class MethodCountInfo {

	private Long id;
	private Long time;//运行时长
	private Long count;//里面的程序运行次数
	private String type;//算法版本
	private String remark;//算法备注
	private Date addTime;//添加时间
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
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
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	

	

}
