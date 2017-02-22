package com.learn.hanjx.thread.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

public class SimpleDateTest {
	public void SimpleDate(){
		SimpleDateFormat sd = new SimpleDateFormat();
		sd.format(new Date());
		//
		
	}
	//method1
	private final ThreadLocal<DateFormat> df= new ThreadLocal<DateFormat>(){
		protected DateFormat initValue(){
			return new SimpleDateFormat("yyyy"); 
		}
	};
	//method2 by jdk8
	public void Jdk8Demo(){
		//date -> instant
		Date date = new Date();
		Instant instant =Instant.now();
		//calendar -> localdatetime 
		//SimpleDateFormat ->  DateTimeFormat
		DateTimeFormatter dt = DateTimeFormatter.BASIC_ISO_DATE;
		//TODO
		TemporalAccessor temporal = null;
		dt.format(temporal);
	}
}
