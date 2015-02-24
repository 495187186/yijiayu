package com.test.tougao.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;


public class DBHelp {
	
	//连接Oralce的驱动
	private static String url ="jdbc:oracle:thin:@localhost:1521:orcl";
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String uid = "tougao";
	private static String pwd = "tougao";
	
	//数据库查询结果集操作变量定义
	private static Connection conn;//Connection 对象代表打开的、与数据源的连接。
	private static PreparedStatement ps;//PreparedStatement对象执行sql语句
	private static CallableStatement cs;//CallableStatement对象以标准形式调用已储存过程，对已储存过程的调用是 CallableStatement对象所含的内容
	private static ResultSet rs;//表示数据库结果集的数据表，通常通过执行查询数据库的语句生成。ResultSet 对象具有指向其当前数据行的指针。
	private static Result r;//Result对象对数据库结果集返回
	
	public DBHelp() throws IOException, ClassNotFoundException, SQLException {
		super();
	}
	
	//定义connect方法连接Oralce
	private static void connect() throws ClassNotFoundException, SQLException{
		Class.forName(driver);
		
		conn = DriverManager.getConnection(url,uid,pwd);
	}
	
	//定义closeConnect方法断开连接Oracle
	private static void closeConnect() throws SQLException{
		if(ps!=null){
	    	ps.close();
	    }
	    if(cs!=null){
	    	cs.close();
	    }
		if(rs != null){
			rs.close();
		}
		if(conn != null){
			conn.close();
		}
	}
	
	//executeQuery方法执行查询语句，ps传递参数从1、2、3……开始编号，所以为ps.setString(i+1, params[i]);sql语句是带问号的占位符形式
	public static Result executeQuery(String sql,String[] params) throws SQLException, ClassNotFoundException{
		connect();
		ps = conn.prepareStatement(sql);
		if(params != null){
			for(int i = 0;i < params.length;i++){
				if(params[i] != null){//通过判断跳过变量数组之间的空格
					ps.setString(i+1, params[i]);
				}
			}
		}
		rs = ps.executeQuery();
		
		r = ResultSupport.toResult(rs);
		closeConnect();
		return r;
	}
	//通过cs来调用数据库中的存储过程，供jsp调用
	public static Result executeCall(String sql,String[] params) throws SQLException, ClassNotFoundException{
		connect();
		cs = conn.prepareCall(sql);
		if(params != null){
			for(int i = 0;i < params.length;i++){
				cs.setString(i+1, params[i]);
			}
		}
		rs = cs.executeQuery();
		r = ResultSupport.toResult(rs);
		closeConnect();
		return r;
	}
	
	//executeUpdate方法执行增、删、改功能
	public static int executeUpdate(String sql,String[] params) throws SQLException, ClassNotFoundException{
		connect();
		ps = conn.prepareStatement(sql);
		if(params != null){
			for(int i = 0;i < params.length;i++){
				ps.setString(i+1, params[i]);
			}
		}
		int n = ps.executeUpdate();//返回值为int类型，返回增、删、改的记录行数；
		closeConnect();
		return n;
	}
}
