package cn.com.db;

import method.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 数据库连接层
 * @author Administrator
 *
 */
public class BaseDao {

	private Config config;

	public BaseDao() {
		super();
		config = Config.createConfig();
	}
	/**
	 * 得到连接
	 * @return
	 */
	public Connection getCon() {
		Connection con = null;
		try {
			Class.forName(config.getProperty("driver"));
			con = DriverManager.getConnection(config.getProperty("url"),
					config.getProperty("user"), config.getProperty("password"));// 建立连接
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return con;
	}
	/**
	 * 释放资源
	 * @param rs
	 * @param stmt
	 * @param conn
	 */
	public void close(ResultSet rs, Statement stmt, Connection conn) {
		if (null != rs) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (null != stmt) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (null != conn) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
