package com.webMagic.service;

import java.io.IOException;

/**
 * Created by han on 2017/12/20.
 */
public class SynThread {
    public static void main(String[] args) {
        Runnable run  = ()->{
            int i = Integer.MAX_VALUE;
            while (i>0){
                i--;
            }
            Runtime exe = Runtime.getRuntime();
            try {
                String str = "E://upload.exe";
                exe.exec(str);
                // 运行指定位置的.exe文件
            } catch (IOException e) {
                System.out.println("Error to run the exe");
                //e.printStackTrace();
            }
            System.out.println("Runnable Over");
        };
        run.run();
        new Thread(){
            public void run(){
                int i = Integer.MAX_VALUE;
                while (i>0){
                    i--;
                }
                System.out.println("Thread Over");
            }
        }.start();
        System.out.println("Runnable");
    }
}
