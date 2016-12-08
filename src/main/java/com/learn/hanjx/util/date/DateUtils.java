package com.learn.hanjx.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	/*
	 * 根据时间戳获取时间
	 */
	public static String getTime(Long timeLong){
		DateFormat format= new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒"); 
		Date date = new Date(timeLong);
		return format.format(date);		
	}
	/*
	 * 根据时间戳获取时间
	 */
	public static Date getTime(String time) throws ParseException{
		DateFormat format= new SimpleDateFormat("yyyy-MM-dd"); 
		return format.parse(time);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String result = "";
		result= DateUtils.getTime(1459408440401L);
		System.out.println(result);
	}

}
