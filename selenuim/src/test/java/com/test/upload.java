package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by han on 2017/12/20.
 */
public class upload {

    public static String URL = "https://mvphjx.github.io/starDemo/LearnAndTest/upload.html";
    public static String URL2 = "http://www.sahitest.com/demo/php/fileUpload.htm";



    @Test
    public void testIe() throws InterruptedException {
        System.setProperty("webdriver.ie.driver","C:\\Users\\han\\Desktop\\github\\browser\\IEDriverServer.exe");
        WebDriver driver = new InternetExplorerDriver();
        driver.get(URL);
        Thread.sleep(2000);
        System.out.println("to open");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        new Thread(){
            public void run(){
                int i = Integer.MAX_VALUE;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Runtime exe = Runtime.getRuntime();
                try {
                    String str = "E://upload.exe";
                    exe.exec(str);
                    // 运行指定位置的.exe文件
                } catch (IOException e) {
                    System.out.println(e.toString());
                }
            }
        }.start();
        System.out.println("Runnable");
        js.executeScript("$(\"#test\").click()");
        js.executeScript("alert(1)");

    }
    @Test
    public void testIe2() throws InterruptedException {
        System.setProperty("webdriver.ie.driver","C:\\Users\\han\\Desktop\\github\\browser\\IEDriverServer.exe");
        WebDriver driver = new InternetExplorerDriver();
        driver.get(URL2);
        Thread.sleep(2000);
        System.out.println("to open");
        WebElement upload = driver.findElement(By.id("file5"));
        upload.click();
        System.out.println("opening");
    }

}
