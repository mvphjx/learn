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



    @Test
    public void testIe() throws InterruptedException {
        System.setProperty("webdriver.ie.driver","C:\\Users\\han\\Desktop\\github\\browser\\IEDriverServer.exe");
        WebDriver driver = new InternetExplorerDriver();
        driver.get(URL);
        Thread.sleep(2000);
        System.out.println("to open");
        WebElement upload = driver.findElement(By.id("test"));
        //String filePath = "D:\\Program Files (x86)\\Mozilla Firefox\\geckodriver.exe";
        //upload.sendKeys(filePath);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("$(\"#test\").click()");
        Runtime exe = Runtime.getRuntime();
        try {
            String str = "E://upload.exe";
            exe.exec(str);
            // 运行指定位置的.exe文件
        } catch (IOException e) {
            System.out.println("Error to run the exe");
            e.printStackTrace();
        }
    }

}
