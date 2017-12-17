package com.learn.hanjx.win32.JNA.time;

import com.sun.jna.*; 
import com.sun.jna.win32.*; 

public interface Kernel32 extends StdCallLibrary 
{ 
   public static class SystemTime extends Structure 
   { 
      public short wYear; 
      public short wMonth; 
      public short wDayOfWeek; 
      public short wDay; 
      public short wHour; 
      public short wMinute; 
      public short wSecond; 
      public short wMilliseconds; 
   } 

   public void GetLocalTime(SystemTime result);
   public void GetSystemTime(SystemTime result);
   public int SetLocalTime(SystemTime sysTime);
} 