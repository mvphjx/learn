package com.learn.Try.log.cast.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.learn.Try.log.cast.model.ShoppingModel;
import com.learn.hanjx.util.date.DateUtils;
import com.learn.hanjx.util.io.FileReadUtils;

public class ImpUtils {
	public static Set<ShoppingModel> listshop = new HashSet<>();
	public  static void init(){
		try {
			init("src/com/Try/log/cast/model/shoppinglog.txt");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public  static void init(String path) throws IOException, ParseException{
		String deaultpath = path;
		BufferedReader bufferedReader=FileReadUtils.readTxtFileReader(deaultpath);
		String lineTxt = null;
		while ((lineTxt = bufferedReader.readLine()) != null) {
			if(lineTxt.indexOf("type")<0){
				continue;
			}
			String [] dates = lineTxt.split(";");
			//System.out.println(dates.length);
			double money = Double.valueOf(dates[4].substring(1));
			Date creattime =DateUtils.getTime(dates[1]);
			StringBuffer type = new StringBuffer("类型");

			if(dates[0].indexOf("1")>=0){
				type.append("-"+ShoppingModel.map.get(1));
			}
			if(dates[0].indexOf("2")>=0){
				type.append("-"+ShoppingModel.map.get(2));
			}
			if(dates[0].indexOf("3")>=0){
				type.append("-"+ShoppingModel.map.get(3));
			}
			if(dates[0].indexOf("4")>=0){
				type.append("-"+ShoppingModel.map.get(4));
			}
			ShoppingModel model = new  ShoppingModel(dates[2], money, dates[3], type.toString(), "", creattime);
			listshop.add(model);
			
		}
		bufferedReader.close();
	}

}
