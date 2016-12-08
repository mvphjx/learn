package com.learn.hanjx.thread.lock;
/*
 * 对象锁是用于对象实例方法，或者一个对象实例上的，类锁是用于类的静态方法或者一个类的class对象上的。
 * 
 * 
 */
public class ClassLock {
	 public void test1()   
	    {    
	         synchronized(ClassLock.class)   
	         {    
	              int i = 5;    
	              while( i-- > 0)   
	              {    
	                   System.out.println(Thread.currentThread().getName() + " : " + i);    
	                   try   
	                   {    
	                        Thread.sleep(500);    
	                   }   
	                   catch (InterruptedException ie)   
	                   {    
	                   }    
	              }    
	         }    
	    }    
	      
	    public static synchronized void test2()   
	    {    
	         int i = 5;    
	         while( i-- > 0)   
	         {    
	              System.out.println(Thread.currentThread().getName() + " : " + i);    
	              try   
	              {    
	                   Thread.sleep(500);    
	              }   
	              catch (InterruptedException ie)   
	              {    
	              }    
	         }    
	    }
		 public void test3()   
		    {    
		         synchronized(this)   
		         {    
		              int i = 5;    
		              while( i-- > 0)   
		              {    
		                   System.out.println(Thread.currentThread().getName() + " : " + i);    
		                   try   
		                   {    
		                        Thread.sleep(500);    
		                   }   
		                   catch (InterruptedException ie)   
		                   {    
		                   }    
		              }    
		         }    
		    } 
	      
	    public static void main(String[] args)   
	    {    
	         final ClassLock myt2 = new ClassLock();    
	         Thread test1 = new Thread(  new Runnable() {  public void run() {  myt2.test1();  }  }, "类锁Class"  );    
	         Thread test2 = new Thread(  new Runnable() {  public void run() { ClassLock.test2();   }  }, "静态方法锁Static"  );
	         Thread test3 = new Thread(  new Runnable() {  public void run() { myt2.test3();   }  }, "对象锁Object"  );   
	         test1.start();
	         //争夺锁，需要等待
	         test2.start();  
	         //对象锁与类锁 无关  可以并发
	         test3.start();
	             
	    }   
	
	

}
