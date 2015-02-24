package com.test.tougao.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

import com.test.tougao.entity.Major;

public class MajorDao {
	//添加专业
	public int add(Major major) throws SQLException, ClassNotFoundException{
		int n=0;
		String sql="insert into tg_major(college_id,major_name) values(?,?)";
		//数组存储sql语句传递参数(若是整形参数都得转换成字符串型，转换方法直接在整形变量后加上空字符，如user.getUserid()+"")
		String[] params = {major.getCollegeId(),major.getMajorName()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//修改专业名称
	public int update(Major major)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql = "update tg_major set college_id=?,major_name=? where major_id = ?";
		//参数对应JSP页面获取值
		String[] params = {major.getCollegeId(),major.getMajorName(),major.getMajorId()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//删除专业
	public int delete(String id)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql= "delete from tg_major where major_id = ?";
		String[] params = {id};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//根据id查询单个专业
	@SuppressWarnings("unchecked")
	public Major findById(String id)throws SQLException, ClassNotFoundException{
		Major major=null;//必须在开始定义了，才能在最后return ,在中间定义则不可以
		String sql = "select * from tg_major where major_id = ?";
		String[] params = {id};
		//返回查询结果
		Result r = DBHelp.executeQuery(sql, params);
		Map map=null;
		   
		if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
			map = r.getRows()[0];//将查询结果赋值给map对象
			major=new Major();
			major.setMajorId(map.get("major_id").toString());
			major.setCollegeId(map.get("college_id").toString());
			major.setMajorName(map.get("major_name").toString()); 
		}
		   return major;
	}
	//查询学院所有专业
	 @SuppressWarnings("unchecked")
	 public List<Major> findCollegeMajor(String collegeid)throws SQLException, ClassNotFoundException{
		 List<Major> list = new ArrayList<Major>();
		 Major major = null;
		 String sql = "select * from tg_major where college_id=?";	
		 String[] params = {collegeid};
		 Result r = DBHelp.executeQuery(sql, params);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 major = new Major();
	    	 major.setMajorId(map.get("major_id").toString());
			 major.setCollegeId(map.get("college_id").toString());
			 major.setMajorName(map.get("major_name").toString()); 
	    	 list.add(major);
	     }
	     return list;
	 }

}
