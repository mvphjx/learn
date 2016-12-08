package com.learn.Try.T2016.T11;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 把大型的字符文件  截取最后的的信息  
 */
public class SpileTXTFile {
	public static void main(String[] args) throws IOException {
		String filename= "C:\\Users\\han\\Downloads\\server (3).log";
		String encoding = "UTF-8";
		int result = 1024*1024*1;
		File file  = new File (filename);
		InputStreamReader read = new InputStreamReader(
				new FileInputStream(file), encoding);// 考虑到编码格式
		BufferedReader bufferedReader = new BufferedReader(read);
		Long size = file.length();
		if(size>result){
			bufferedReader.skip(size-result);			
		}
		File newfile = new File (filename+System.currentTimeMillis()+".txt");
		OutputStreamWriter w = new OutputStreamWriter(
				new FileOutputStream(newfile), encoding);
		BufferedWriter bufw = new  BufferedWriter(w);
		String lineTxt = "";
		while ((lineTxt = bufferedReader.readLine()) != null) {
			bufw.write(lineTxt+"\n");
		}
	}
}
