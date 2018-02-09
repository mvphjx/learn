package com.learn.hanjx.win32.JNA.time;

import com.sun.jna.Native;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;





public class LocalTime 
{ 
	public static String getLocalTime() 
	{
	    String date=null;
	    Kernel32 lib = (Kernel32) Native.loadLibrary ("kernel32",Kernel32.class); 
	    Kernel32.SystemTime time = new Kernel32.SystemTime (); 
	    lib.GetLocalTime(time); 

	    date=time.wYear+"-"+time.wMonth+"-"+time.wDay+" "+time.wHour+":"+time.wMinute+":"+time.wSecond;
	    return date;
	}
	
	public static String getSystemTime() 
	{
	    String date=null;
	    Kernel32 lib = (Kernel32) Native.loadLibrary ("kernel32",Kernel32.class);
	    Kernel32.SystemTime time = new Kernel32.SystemTime (); 
	    lib.GetSystemTime(time); 
	      
	    date=time.wYear+"-"+time.wMonth+"-"+time.wDay+" "+time.wHour+":"+time.wMinute+":"+time.wSecond;
	    return date;
	}
	//此方法需要系统管理员权限。
	public static int setLocalTime(String time)
	{
	   int flag=0;
	   Kernel32 lib = (Kernel32) Native.loadLibrary ("kernel32",Kernel32.class); 
	   SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   Date date;
	   try 
	    {
		Kernel32.SystemTime sysTime = new Kernel32.SystemTime();
		date = sdf.parse(time);
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		sysTime.wHour =  (short) calendar.get(Calendar.HOUR_OF_DAY);
                sysTime.wMinute =  (short) calendar.get(Calendar.MINUTE);
            	sysTime.wSecond =  (short) calendar.get(Calendar.SECOND);
            	sysTime.wMilliseconds=(short) calendar.get(Calendar.MILLISECOND);          
		sysTime.wYear = (short) calendar.get(Calendar.YEAR);
		sysTime.wMonth = (short) (calendar.get(Calendar.MONTH)+1);
		sysTime.wDay = (short) calendar.get(Calendar.DAY_OF_MONTH);
		sysTime.wDayOfWeek=(short) calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
			
		flag = lib.SetLocalTime(sysTime);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
   public static void main (String [] args) 
   { 
     System.out.println("LocalTime:"+getLocalTime());
     System.out.println("SystemTime:"+getSystemTime());
     System.out.println(setLocalTime("2017-08-21 20:05:23"));
   } 
} 