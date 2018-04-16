package com.learn.hanjx.thread.lock;

/*
 * 对象锁是用于对象实例方法，或者一个对象实例上的，
 * 类锁是用于类的静态方法或者一个类的class对象上的。
 */
public class SyncDemo {
    /**
     *类锁
     */
    public void test1() {
        synchronized (SyncDemo.class) {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " 类锁: " + i);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ie) {
                }
            }
        }
    }

    /**
     *静态方法锁Static
     */
    public static synchronized void test2() {
        int i = 5;
        while (i-- > 0) {
            System.out.println(Thread.currentThread().getName() + " 静态方法锁: " + i);
            try {
                Thread.sleep(50);
            } catch (InterruptedException ie) {
            }
        }
    }

    /**
     *对象锁
     */
    public void test3() {
        synchronized (this) {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " 对象锁: " + i);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ie) {
                }
            }
        }
    }

    public static void main(String[] args) {
        final SyncDemo myt2 = new SyncDemo();
        Thread test1 = new Thread(new Runnable() {
            public void run() {
                myt2.test1();
            }
        }, "类锁Class");
        Runnable run1  = ()->{myt2.test1();};
        Thread test2 = new Thread(new Runnable() {
            public void run() {
                SyncDemo.test2();
            }
        }, "静态方法锁Static");
        Runnable run2  = ()->{SyncDemo.test2();};
        Thread test3 = new Thread(new Runnable() {
            public void run() {
                myt2.test3();
            }
        }, "对象锁Object");
        Runnable run3  = ()->{myt2.test3();};
        //对象锁与类锁 无关  可以并发
//        Thread vs Runnable diffirent？
        test3.start();
        run3.run();

        //类锁 静态方法争夺锁，需要等待
        test2.start();
        //run2.run();
        test1.start();
        //run1.run();

    }


}
