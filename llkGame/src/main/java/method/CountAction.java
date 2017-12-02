package method;

import java.util.Date;
import java.util.List;

import llk.LookMain;

import cn.com.beans.MethodCountInfo;
import cn.com.daos.CountDao;

/*
 * 用来保存和统计运行时间
 * 
 */
public class CountAction {
	static CountDao  dao  = null;
	
	public  static   void SaveAndView(MethodCountInfo model){
		try{
		if(dao==null){
			dao=new CountDao();
		}
		dao.save(model);
		List<MethodCountInfo> list=dao.getCount();
		System.out.println("Type--------Remark-----------Time-----Count");
		for(MethodCountInfo info:list){
			System.out.println(info.getType()+"--------"+info.getRemark()+"---------"+info.getTime()+"-------"+info.getCount());
		}
		}catch(Exception e){
			System.out.println("数据库报错，无法统计");
		}
	}
	public static void main(String[] args) throws Exception {
		MethodCountInfo model = new MethodCountInfo();
		model.setRemark("连连看算法");
		model.setType("llk");
		model.setTime(0L);
		model.setCount(1L);
		CountAction.SaveAndView(model);

	}
	
}
