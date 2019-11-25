package com.learn.Try.log.cast.common;

import java.util.Set;

import org.junit.Test;

import com.learn.Try.log.cast.model.ShoppingModel;

public class CountTest {
	@Test
	public void Try(){
		
	}
	/*
	 * 统计不同类型的消费情况
	 */
	public static void main(String[] args){
		ImpUtils.init();
		Set<ShoppingModel> set = ImpUtils.listshop;
		double alltype2 = 0.0;
		double alltype12 = 0.0;
		for(ShoppingModel model:set){
			if(model.getType().indexOf(ShoppingModel.map.get(2))>=0){
				alltype2= alltype2 +model.getMoney();
				System.out.println(model.toString());
				if(model.getType().indexOf(ShoppingModel.map.get(1))>=0){
					alltype12 = alltype12+model.getMoney();
				}
				String result = "allmoney:"+alltype2;
				result=result + "("+ShoppingModel.map.get(1)+":"+alltype12+"+"+ShoppingModel.map.get(2)+":"+(alltype2-alltype12)+")";
				System.out.println(result);
			}
		}
	}

}
