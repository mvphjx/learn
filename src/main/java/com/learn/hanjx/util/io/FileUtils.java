package com.learn.hanjx.util.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtils {
	public static void wirterFile(String fileName,byte[] bytes) throws IOException{
		//filePath 不存在创建
		//fileName 当前时间.dat
		//
		
		FileOutputStream fw = new FileOutputStream(fileName);
		fw.write(bytes);;
	}
	/**
	 * 把数据写入文件。
	 * @param fileName
	 * @param data
	 * @return
	 */
	public	static	int		saveDataToFile(String fileName, byte[] data, int offset, int length)
	{
		try
		{
			FileOutputStream	fo = new FileOutputStream(fileName);
			fo.write(data, offset, length);
			fo.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return	1;
	}
	public static BufferedReader readTxtFileReader(String filePath) {
		try {
			String encoding = "UTF-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				return bufferedReader;
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		return null;

	}
	public static void readTxtFileAndPrint(String filePath) {
		try {
			String encoding = "UTF-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					System.out.println(lineTxt);
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}

	}
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader=readTxtFileReader("src/com/Try/log/cast/model/shoppinglog.txt");
		String lineTxt = null;
		while ((lineTxt = bufferedReader.readLine()) != null) {
			System.out.println(lineTxt);
		}
		bufferedReader.close();
	}
}
