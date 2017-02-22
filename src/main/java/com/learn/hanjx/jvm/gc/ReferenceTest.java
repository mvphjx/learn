package com.learn.hanjx.jvm.gc;
/**
 * jvm回收机制
 *
以下对象会被认为是root对象：
被启动类（bootstrap加载器）加载的类和创建的对象
jvm运行时方法区类静态变量(static)引用的对象
jvm运行时方法去常量池引用的对象
jvm当前运行线程中的虚拟机栈变量表引用的对象
本地方法栈中(jni)引用的对象

强引用（Strong Reference）	不符合垃圾收集
软引用（Soft Reference）	垃圾收集可能会执行，但会作为最后的选择
弱引用（Weak Reference）	符合垃圾收集
虚引用（Phantom Reference）	符合垃圾收集

 */
/*
 * 被Weak Reference的对象在一定的时候会被jvm回收。
 * 但是强引用就不会出现这种状态。软引用在内存足够的情况下也不会被回收
 */
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class ReferenceTest  {
	public static final Map<Integer, Reference> map = new HashMap<Integer, Reference>();
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            map.put(i, new WeakReference(new ReferenceObject(i)));
            //map.put(i, new SoftReference(new ReferenceObject(i)));
        }
        System.gc();
        int i = 0;
        for (Reference r : map.values()) {
            if (r.get() == null) {
                i++;
            }
        }
        System.out.println("被回收的对象数:" + i);
    }
    static class ReferenceObject {
        private static int    i;
        private byte[] b;
        public ReferenceObject(int i) {
            this.i = i;
            b = new byte[1024 *10];
        }
    }
}
