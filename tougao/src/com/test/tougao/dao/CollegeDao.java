package com.test.tougao.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

import com.test.tougao.entity.College;

public class CollegeDao {
	//添加学院
	public int add(College college) throws SQLException, ClassNotFoundException{
		int n=0;
		String sql="insert into tg_college(college_name) values(?)";
		//数组存储sql语句传递参数(若是整形参数都得转换成字符串型，转换方法直接在整形变量后加上空字符，如user.getUserid()+"")
		String[] params = {college.getCollegeName()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//修改学院名称
	public int update(College college)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql = "update tg_college set college_name=? where college_id = ?";
		//参数对应JSP页面获取值
		String[] params = {college.getCollegeName(),college.getCollegeId()};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//删除学院
	public int delete(String id)throws SQLException, ClassNotFoundException{
		int n=0;
		String sql= "delete from tg_college where college_id = ?";
		String[] params = {id};
		n = DBHelp.executeUpdate(sql, params);//n是数据库中受影响的记录行数，即插入记录条数
		return n;
	}
	//根据id查询单个学院
	@SuppressWarnings("unchecked")
	public College findById(String id)throws SQLException, ClassNotFoundException{
		College college=null;//必须在开始定义了，才能在最后return ,在中间定义则不可以
		String sql = "select * from tg_college where college_id = ?";
		String[] params = {id};
		//返回查询结果
		Result r = DBHelp.executeQuery(sql, params);
		Map map=null;
		   
		if(r.getRowCount() == 1){//如果结果集中有一条数据，即查找成功
			map = r.getRows()[0];//将查询结果赋值给map对象
			college=new College();
			college.setCollegeId(map.get("college_id").toString());
			college.setCollegeName(map.get("college_name").toString());
			 
		}
		   return college;
	}
	//查询所有学院
	 @SuppressWarnings("unchecked")
	 public List<College> findAllCollege()throws SQLException, ClassNotFoundException{
		 List<College> list = new ArrayList<College>();
		 College college = null;
		 String sql = "select * from tg_college";	
		 Result r = DBHelp.executeQuery(sql, null);
		 Map map = null;
		 //循环将每条数据查出，然后添加到list中
	     for(int i = 0;i <r.getRowCount();i++){
	    	 map = r.getRows()[i];//map对象是键-值类型，只起过渡作用，且map每次只能过渡一条记录，不能像list接收整个结果集
	    	 college = new College();
	    	 college.setCollegeId(map.get("college_id").toString());
			 college.setCollegeName(map.get("college_name").toString());
	    	 list.add(college);
	     }
	     return list;
	 }

}
