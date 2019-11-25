package com.learn.Try.T2017.T09.classload;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * class文件加密、解密的代码：
 */
public class MyCipher {

    public static final String path = "D:\\workspace\\github\\learn\\target\\classes\\";

    public static void main(String[] args) {
        enCipherClass("D:\\workspace\\github\\learn\\target\\classes\\com\\learn\\Try\\T2017\\T09\\classload\\"+"MyClassBase.class");
    }
    //加密
    public static String enCipherClass(String path) {
        File classFile = new File(path);
        if (!classFile.exists()) {
            System.out.println("File does not exist!");
            return null;
        }

        String cipheredClass = classFile.getParent() + File.separator + "Cipher" + classFile.getName();
        System.out.println("enCipherClass() cipheredClass=" + cipheredClass);

        try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(classFile));
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(cipheredClass));) {

            int data = 0;
            while ((data = is.read()) != -1) {
                out.write(data ^ 0xFF);
            }

            out.flush();
            is.close();
            out.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return cipheredClass;
    }
//解密方法 deCihperClass 把输入的 class 文件， 变为 byte [] 返回。
    public static byte[] deCihperClass(String path) {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("deCihperClass() File:" + path + " not found!");
            return null;
        }

        System.out.println("deCihperClass() path=" + path);

        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int data = 0;
            while ((data = in.read()) != -1) {
                out.write(data ^ 0xFF);
            }
            in.close();
            out.flush();
            out.close();

            return out.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}