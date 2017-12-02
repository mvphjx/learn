package cn.com.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.com.beans.MethodCountInfo;
import cn.com.db.BaseDao;



/**
 * 操作信息层
 * @author Administrator
 *
 */
public class CountDao extends BaseDao {
	
	
	/*
	 * 保存
	 */
	public boolean save(MethodCountInfo model){
		Connection conn = null;
		Statement stmt = null;  //不安全 sql注入
		String sql = "insert  into methodcount VALUES(count_seq.nextval,?,?,?,?,sysdate)";
		PreparedStatement pstmt = null;//比较安全
		ResultSet rs = null;
		try {
			conn = this.getCon();
			pstmt = conn.prepareStatement(sql); //使用预处理的方式创建对象  
			pstmt.setLong(1, model.getCount()); //第1个？号的内容  
	        pstmt.setLong(2, model.getTime()); //第2个？号的内容  
	        pstmt.setString(3, model.getRemark()); 
	        pstmt.setString(4, model.getType());  
	        pstmt.executeUpdate(); //执行SQL 语句，更新数据库  
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		
		
		return false;
		
	}
	/*
	 * 查找统计结果
	 */
	public  List<MethodCountInfo>  getCount(){
		Connection conn = null;
		List<MethodCountInfo> list  =  new ArrayList<MethodCountInfo>();
		String sql = "select max(id),type,max(remark),avg(count),avg(time),max(addtime) from methodcount  group  by type";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = this.getCon();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				MethodCountInfo model = new MethodCountInfo();
				model.setId(rs.getLong(1));
				model.setType(rs.getString(2));
				model.setRemark(rs.getString(3));
				model.setCount(rs.getLong(4));
				model.setTime(rs.getLong(5));
				model.setAddTime(rs.getDate(6));
				list.add(model);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		
		return list;
	}
	
	/*
	 * 鉴于java中取seq效率太低，废弃不用  不实现了
	 */
	private Long getSeq(){
		return null;
	}
	
}
