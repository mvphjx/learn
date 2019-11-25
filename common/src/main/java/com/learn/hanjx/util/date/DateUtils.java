package com.learn.hanjx.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	/*
	 * 字符串2 获取 时间
	 */
	public static String getTime(String time,boolean isBegin) throws ParseException{
		int year=1970, month=0, dayOfMonth=0, hour=0, minute=0, second=0;
		int length = time.length();
		if(length>=4){

		}
		if(length>=4){

		}
		if(length>=4){

		}
		switch (length){
			case 8:
				dayOfMonth = Integer.valueOf(time.substring(6,8));
				month= Integer.valueOf(time.substring(4,6));
				year = Integer.valueOf(time.substring(0,4));
			case 6:
				month= Integer.valueOf(time.substring(4,6));
				year = Integer.valueOf(time.substring(0,4));
			case 4:
				year = Integer.valueOf(time.substring(0,4));
				break;


		}
		LocalDateTime of = LocalDateTime.of(year, month, dayOfMonth, hour, minute, second);
		System.out.println(of);
		Pattern p= Pattern.compile("(\\d{4})(\\d{2})(\\d{2})");
		p= Pattern.compile("(\\d{4}(\\d{2}?(\\d{2}?)))");
		Matcher m=p.matcher(time);
		if(m.find()){
			System.out.println("日期:"+m.group());
			System.out.println("年:"+m.group(1));
			System.out.println("月:"+m.group(2));
			System.out.println("日:"+m.group(3));
		}
		return null;
	}
	/**
	 * select HSABIS_STR_TO_DATE('2019','yyyyMMddHHmmss') from dual
	 * @param args
	 */
	public static void main(String[] args) throws ParseException
	{
		String result = "";
		result= DateUtils.getTime(1459408440401L);
		System.out.println(result);
		result= DateUtils.getTimeJDK8(1459408440401L);
		System.out.println(result);
		String timeJDK82 = DateUtils.getTime("201901",false);
		System.out.println(timeJDK82);
	}



}
