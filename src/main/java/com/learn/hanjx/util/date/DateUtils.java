package com.learn.hanjx.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
	/*
	 * 根据  时间戳  获取  字符串
	 */
	public static String getTime(Long timeLong){
		DateFormat format= new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒"); 
		Date date = new Date(timeLong);
		return format.format(date);		
	}
	/*
	 * 字符串 获取 时间
	 */
	public static Date getTime(String time) throws ParseException{
		DateFormat format= new SimpleDateFormat("yyyy-MM-dd"); 
		return format.parse(time);
	}
	
	//并发优化，线程局部变量
	private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	}; 
	
	/*
	 * jdk8并发优化，线程安全
	 * Java 8：健壮、易用的时间/日期API
	 * Instant 代替 Date，
	 * LocalDateTime 代替 Calendar，
	 * DateTimeFormatter代替Simpledateformatter，
	*/
	/*
	 * 根据 时间戳 获取 字符串
	 */
	public static String getTimeJDK8(Long timeLong){
		DateTimeFormatter format=DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒");
		LocalDateTime date =
	            LocalDateTime.ofInstant(Instant.ofEpochMilli(timeLong), ZoneId.systemDefault());
		return format.format(date);
	}
	/*
	 * 字符串 获取 时间
	 */
	public static LocalDateTime getTimeJDK8(String time) throws ParseException{
		DateTimeFormatter format=DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒"); 
		LocalDateTime date =LocalDateTime.parse(time,format);
		return date;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String result = "";
		result= DateUtils.getTime(1459408440401L);
		System.out.println(result);
		result= DateUtils.getTimeJDK8(1459408440401L);
		System.out.println(result);
	}
	
	

}
