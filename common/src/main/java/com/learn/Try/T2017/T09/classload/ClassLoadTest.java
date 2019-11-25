package com.learn.Try.T2017.T09.classload;

/**
 * Created by han on 2017/9/25.
 */
import java.io.File;
public class ClassLoadTest {
    public static void main(String[] args) throws Exception {
        /**
         * 自定义的 ClassLoader 会先把 类加载操作先委派给它的parent， 也就是系统默认的类加载器，
         * 如果系统默认的类加载器，找不到  classload.MyClassBase 这个类，才会调用自己的类加载器
         */
        ClassLoader loader = new ClassLoader() {
            @Override
            public Class<?> findClass(String name) {

                System.out.println("findClass() name = " + name);

                String baseName = name.replace(".",File.separator);
                String path = baseName.substring(0,baseName.lastIndexOf(File.separator)+1);
                String className = baseName.substring(baseName.lastIndexOf(File.separator)+1);
                baseName= path + "Cipher" + className + ".class";
                String[] fileNameElements = { MyCipher.path, baseName };
                byte[] data = MyCipher.deCihperClass(String.join(File.separator, fileNameElements));
                Class<?> clz = defineClass(name, data, 0, data.length);
                return clz;
            }
        };
        int count= 0;
        Runnable run = () ->
        {
            while(true){
                Class<?> clz = null;
                try
                {
                    clz = loader.loadClass("com.learn.Try.T2017.T09.classload.MyClassBase");
                    System.out.println("\n loaded class:" + clz.getName() + " by " + clz.getClassLoader());
                    MyClassInterface obj = (MyClassInterface) clz.newInstance();
                    obj.say();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
                try
                {
                    Thread.sleep(5000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };
        run.run();
    }
}