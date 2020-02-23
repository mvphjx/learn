package com.learn.hanjx.concurrent.thread.daemon;
/*
 * 守护线程结束的时候  不一定会运行finally
 */
public class DaemonAndFinally implements Runnable {
    public static void main(String[] args) {
        System.out.println("Daemon Thread test..");
        Thread t = new Thread(new DaemonAndFinally());
        t.setDaemon(true);
        t.setName("守护线程");
        Thread tuser = new Thread(new DaemonAndFinally());
        tuser.setName("用户线程");
        t.start();
        tuser.start();
    }
    public void run() {
        boolean isDaemon = Thread.currentThread().isDaemon();
        String name = Thread.currentThread().getName();
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(name);
            sb.append(" start Thread, daemon:");
            sb.append(isDaemon);
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
            System.out.println(name  +" Finally has run.");
        }
    }
}
