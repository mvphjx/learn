package com.learn.hanjx.thread.daemon;
/*
 * 守护线程结束的时候  不一定会运行finally
 */
public class DaemonAndFinally implements Runnable { 
    public static void main(String[] args) {  
        System.out.println("Daemon Thread test..");  
        Thread t = new Thread(new DaemonAndFinally());  
        t.setDaemon(true);  
        Thread tuser = new Thread(new DaemonAndFinally());
        t.start();
        tuser.start();
    }  
    public void run() {  
        boolean isDaemon = Thread.currentThread().isDaemon();  
        try {  
            StringBuffer sb = new StringBuffer();  
            sb.append("start Thread, daemon:" + isDaemon);  
            if (isDaemon) {  
                sb.append(", 'finally does not run!'");
                Thread.sleep(1000L);
            } else {  
                sb.append(", 'finally does run!'");  
            }  
            System.out.println(sb.toString()); 
            Thread.sleep(1100L);
        } catch (Exception e) {  
            if (e instanceof InterruptedException) {  
                System.out.println("Exiting via InterruptedException");  
            }  
        } finally {  
            System.out.println("Finally has run.");  
        }  
    }  
} 