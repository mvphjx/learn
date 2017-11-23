package com.learn.hanjx.thread.lock.badLock;

import java.io.IOException;
/**
 * 产生死锁的四个必要条件：
 （1） 互斥条件：一个资源每次只能被一个进程使用。
 （2） 请求与保持条件：一个进程因请求资源而阻塞时，对已获得的资源保持不放。
 （3） 不剥夺条件:进程已获得的资源，在末使用完之前，不能强行剥夺。
 （4） 循环等待条件:若干进程之间形成一种头尾相接的循环等待资源关系。

 * Created by han on 2017/11/23.
 *
 */
public class RunnableTest implements Runnable {
    private Integer a, b;

    public RunnableTest(Integer a, Integer b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        synchronized (a) {
            synchronized (b) {
                System.out.println("" + System.currentTimeMillis() + ":" + (a + b));
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        for (int i = 0; i < 100; i++) {
            new Thread(new RunnableTest(1, 2)).start();
            new Thread(new RunnableTest(2, 1)).start();
        }
    }
}
